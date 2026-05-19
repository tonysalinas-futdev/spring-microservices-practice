package com.tony.api_gateway.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    private final JwtProperties properties;

    public JwtConfig(JwtProperties properties) {
        this.properties = properties;
    }
    @Bean
    public ReactiveJwtDecoder jwtDecoder(){
        SecretKey key=new SecretKeySpec(properties.getSecret().getBytes(),"HmacSHA256");

        return NimbusReactiveJwtDecoder.withSecretKey(key).build();

    }
}
