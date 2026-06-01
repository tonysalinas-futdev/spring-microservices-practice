package com.tony.microservices.feign.client;

import com.tony.microservices.feign.models.Role;
import com.tony.microservices.feign.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "user-microservice")
public interface UserClient {
    @GetMapping("api/v1/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id);


    @PutMapping("api/v1/admin/role/{role}/id/{id}")
    public ResponseEntity<User> addNewUserRole(@PathVariable Role role, @PathVariable Long id);


    @DeleteMapping("api/v1/admin/role/{role}/id/{id}")
    public ResponseEntity<User> deleteUserRole(@PathVariable Role role, @PathVariable Long id);
}
