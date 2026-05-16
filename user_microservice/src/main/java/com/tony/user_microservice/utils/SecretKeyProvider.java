package com.tony.user_microservice.utils;


import com.tony.user_microservice.security.config.JwtProperties;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretKeyProvider {
  private final JwtProperties properties;

  public SecretKey get() {
    byte[] bytes = Decoders.BASE64.decode(properties.getSecret());
    return Keys.hmacShaKeyFor(bytes);
  }
}
