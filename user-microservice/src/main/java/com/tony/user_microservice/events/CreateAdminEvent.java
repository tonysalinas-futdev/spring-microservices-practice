package com.tony.user_microservice.events;

import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.repository.UserRepository;
import com.tony.user_microservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
/*
@Component
@RequiredArgsConstructor
public class CreateAdminEvent {
    private final UserService service;
    private final UserRepository repo;

    @EventListener
    void execute(ApplicationReadyEvent event){
        if (repo.findByEmail("admin@gmail.com").isPresent()){
            return;
        }
        AdminCreateUserDTO dto= AdminCreateUserDTO.builder()
                .fullName("User Admin")
                .email("admin@gmail.com")
                .role(Role.ADMIN)
                .password("Abcd1234#")
                .age(32)
                .build();
        service.createUserByAdmin(dto);
    }
}
*/