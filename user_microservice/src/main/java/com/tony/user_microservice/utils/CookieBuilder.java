package com.tony.user_microservice.utils;


import com.tony.user_microservice.dtos.AuthResponse;
import com.tony.user_microservice.security.config.JwtProperties;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieBuilder {
    private final JwtProperties properties;

    public ResponseCookie buildAccessTokenCookie(AuthResponse authResponse){
        return   ResponseCookie.from("access_token",authResponse.getAccessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(properties.getExpiration())
                .build();
    }

    public ResponseCookie buildRefreshTokenCookie(AuthResponse authResponse){
        return ResponseCookie.from("refresh_token",authResponse.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(properties.getRefreshTokenExpiration())
                .build();
    }
}
