package com.tony.user_microservice.utils;



import com.tony.user_microservice.model.Permissions;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.security.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final SecretKeyProvider provider;
  private final JwtProperties properties;

  public String createAccessToken(User user) {
    return buildToken(user, properties.getExpiration(), "access_token");
  }

  public String createRefreshToken(User user) {
    return buildToken(user, properties.getRefreshTokenExpiration(), "refresh_token");
  }

  public String buildToken(User user, Long expiration, String type) {
    return Jwts.builder()
        .id(UUID.randomUUID().toString())
        .claim("token_type", type)
        .claim("id", user.getId().toString())
            .claim("role",user.getRol())
            .claim("permissions",user.getRol().getPermissions().stream().map(Permissions::getName).toList())

        .subject(user.getEmail())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(provider.get())
        .compact();
  }
}
