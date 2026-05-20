package com.tony.user_microservice.security.filters;

import com.tony.user_microservice.exceptions.InvalidTokenException;
import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.model.Token;

import com.tony.user_microservice.services.TokenService;
import com.tony.user_microservice.services.TokenValidationService;

import com.tony.user_microservice.utils.JwtTokenParser;
import com.tony.user_microservice.utils.TokenExtractor;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/*
@Slf4j
@Component
@RequiredArgsConstructor

public class JwtAuthFilter extends OncePerRequestFilter {
  private final UserDetailsService userDetailsService;
  private final TokenValidationService validationService;
  private final JwtTokenParser parser;


  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().contains("/auth")) {
      filterChain.doFilter(request, response);
      return;
    }

        String tokenValue=TokenExtractor.extract(request.getHeader(HttpHeaders.AUTHORIZATION));
        Claims payload= parser.parse(tokenValue);
        if (payload.get("token_type").equals("access_token")){
            if (validationService.isTokenExpired(tokenValue)){
                throw new InvalidTokenException("Token Expired");
            }
        }
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(payload.getSubject());

      var authReponse =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authReponse.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authReponse);


    filterChain.doFilter(request, response);
  }

}
*/