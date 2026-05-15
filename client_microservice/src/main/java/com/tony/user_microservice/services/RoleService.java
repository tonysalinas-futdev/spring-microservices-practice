package com.tony.user_microservice.services;

import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.model.Roles;
import com.tony.user_microservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository repo;

    public Roles getByNameOrThrow(Role name){
        return repo.findByName(name).orElseThrow(()-> new NotFoundException("Role not found"));
    }

}
