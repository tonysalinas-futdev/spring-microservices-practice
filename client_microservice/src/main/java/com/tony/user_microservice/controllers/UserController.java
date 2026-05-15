package com.tony.user_microservice.controllers;

import com.tony.user_microservice.dtos.GetUserProfileDTO;
import com.tony.user_microservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/me")
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<GetUserProfileDTO> getProfile(Authentication authentication){
        GetUserProfileDTO dto=service.getProfile(authentication.getName());
        return ResponseEntity.ok(dto);
    }
}
