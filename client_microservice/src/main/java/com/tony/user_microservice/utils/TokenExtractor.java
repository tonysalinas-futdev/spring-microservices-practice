package com.tony.user_microservice.utils;


import com.tony.user_microservice.exceptions.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenExtractor {

  public static String extract(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer")) {
      throw new InvalidTokenException("Invalid token");
    }
    return authHeader.substring(7);

  }
}
