package com.tony.user_microservice.services;

import com.tony.user_microservice.dtos.AuthResponse;
import com.tony.user_microservice.dtos.CreateUserBySignUp;
import com.tony.user_microservice.dtos.LoginDTO;
import com.tony.user_microservice.model.Token;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.utils.JwtTokenParser;
import com.tony.user_microservice.utils.JwtTokenProvider;
import com.tony.user_microservice.utils.TokenExtractor;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class AuthService {
  private final TokenService tokenService;
  private final JwtTokenProvider provider;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenParser parser;
  private final TokenValidationService validationService;


  public AuthResponse login(@Valid LoginDTO dto) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
    User user = userService.getByEmailOrThrow(dto.getEmail());
    tokenService.revokeAllValidTokensForUser(user.getId());
    String accessToken=provider.createAccessToken(user);
    String refreshToken=provider.createRefreshToken(user);
    tokenService.create(user,refreshToken);
    return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }


  public AuthResponse signUp(@Valid CreateUserBySignUp dto) {
    User user = userService.createUserBySignUp(dto);
      String accessToken=provider.createAccessToken(user);
      String refreshToken=provider.createRefreshToken(user);
      tokenService.create(user,refreshToken);
      return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }

  public AuthResponse refreshToken(String refreshToken) {
      Token token= tokenService.getToken(refreshToken);
      validationService.validateRefreshToken(token);
      Claims payload=parser.parse(token.getValue());

      User user = userService.getByEmailOrThrow(payload.getSubject());
      tokenService.revokeAllValidTokensForUser(user.getId());
      String newRefreshToken=provider.createRefreshToken(user);
      String accessToken=provider.createAccessToken(user);

      tokenService.create(user,newRefreshToken);
      return AuthResponse.builder().accessToken(accessToken).refreshToken(newRefreshToken).build();
  }
}
