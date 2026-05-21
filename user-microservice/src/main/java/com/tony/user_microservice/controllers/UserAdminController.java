package com.tony.user_microservice.controllers;

import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin")

public class UserAdminController {
    private final UserService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.getByIdOrThrow(id));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('CREATE_USER')")
    public ResponseEntity<User> createUser(@RequestBody AdminCreateUserDTO dto){
        return ResponseEntity.status(201).body(service.createUserByAdmin(dto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);

    }
}
