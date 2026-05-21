package com.tony.user_microservice.controllers;

import com.tony.user_microservice.dtos.AuthResponse;
import com.tony.user_microservice.dtos.CreateUserBySignUp;
import com.tony.user_microservice.dtos.LoginDTO;
import com.tony.user_microservice.services.AuthService;
import com.tony.user_microservice.utils.CookieBuilder;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/v1/auth")
    @AllArgsConstructor
    public class AuthControllers {
        private final AuthService service;
        private final CookieBuilder cookieBuilder;


        @PostMapping("/sign_up")
        public ResponseEntity<?> signUp(@RequestBody CreateUserBySignUp dto) {
            AuthResponse tokens = service.signUp(dto);

            ResponseCookie accessCookie=cookieBuilder.buildAccessTokenCookie(tokens);
            ResponseCookie refreshCookie=cookieBuilder.buildRefreshTokenCookie(tokens);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,accessCookie.toString()).header(HttpHeaders.SET_COOKIE,refreshCookie.toString()).build();
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
            AuthResponse tokens = service.login(dto);
            ResponseCookie accessCookie=cookieBuilder.buildAccessTokenCookie(tokens);
            ResponseCookie refreshCookie=cookieBuilder.buildRefreshTokenCookie(tokens);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,accessCookie.toString()).header(HttpHeaders.SET_COOKIE,refreshCookie.toString()).build();

        }
        @PutMapping("/refresh")
        public ResponseEntity<?> refreshToken(@RequestBody String refreshToken){
            AuthResponse response=service.refreshToken(refreshToken);
            ResponseCookie accessCookie=cookieBuilder.buildAccessTokenCookie(response);
            ResponseCookie refreshCookie=cookieBuilder.buildRefreshTokenCookie(response);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,accessCookie.toString()).header(HttpHeaders.SET_COOKIE,refreshCookie.toString()).build();



        }
    }


