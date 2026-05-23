package com.tony.api_gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping("/me")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<?> getData(Authentication authentication, @AuthenticationPrincipal Jwt jwt){

        return Mono.just(Map.of("principal",jwt.getClaims(), "authorities", authentication.getAuthorities()));
    }
}
