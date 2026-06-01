package com.tony.user_microservice;

import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.repository.UserRepository;
import com.tony.user_microservice.services.RoleService;
import com.tony.user_microservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateAdminEvent {
    private final UserService userService;
    private final UserRepository userRepository;

    @EventListener
    public void execute(ApplicationReadyEvent event){
        if (userRepository.findByEmail("admin@gmail.com").isPresent()){
            return;
        }
        AdminCreateUserDTO dto= AdminCreateUserDTO.builder()
                .fullName("Admin User")
                .email("admin@gmail.com")
                .password("Abcd1234#")
                .role(Role.ADMIN)
                .build();
        userService.createUserByAdmin(dto);
    }
}
