package com.tony.user_microservice.utils;


import com.tony.user_microservice.security.config.JwtProperties;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecretKeyProvider {
  private final JwtProperties properties;

  public PrivateKey getPrivateKey() {
    byte[] bytes = Base64.getDecoder().decode(properties.getPrivateKey());
    try {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(new PKCS8EncodedKeySpec(bytes));
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
        throw new JwtException("Error con la clave de jwt");
    }
  }
  public PublicKey getPublicKey(){
      byte[] bytes = Base64.getDecoder().decode(properties.getPublicKey());
      try {
          KeyFactory kf = KeyFactory.getInstance("RSA");
          return kf.generatePublic(new X509EncodedKeySpec(bytes));
      } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
          log.error("Error JWT", e);
          throw new JwtException("Error con la clave pública para jwt");

      }

  }
}
