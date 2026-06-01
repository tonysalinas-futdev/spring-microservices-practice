package com.tony.user_microservice.services;

import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.exceptions.UserCreationError;
import com.tony.user_microservice.exceptions.UserOperationException;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
@Sql(scripts = "classpath:clean.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:init.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class TestUserService {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleService roleService;


    @Test
    void shouldCreateUserByAdminSuccessfully(){
        AdminCreateUserDTO dto=AdminCreateUserDTO.builder()
                .fullName("Juan Antonio Chao Salinas")
                .email("myemail@gmail.com")
                .age(20)
                .role(Role.CLIENT)
                .password("Abc12345#sd")
                .build();
        User user=service.createUserByAdmin(dto);

        Assertions.assertTrue(user.getRoles().stream().anyMatch(role->role.getName().equals(Role.CLIENT)));
        Assertions.assertEquals(dto.getFullName(),user.getFullName());
    }

    @Test
    void shouldFailToCreateUserWithExistingEmail(){
        AdminCreateUserDTO dto=AdminCreateUserDTO.builder()
                .fullName("Juan Antonio Chao Salinas")
                .email("email@gmail.com")
                .age(20)
                .role(Role.CLIENT)
                .password("Abc12345#sd")
                .build();

         service.createUserByAdmin(dto);

        Assertions.assertThrows(UserCreationError.class, ()->service.createUserByAdmin(dto));

    }
    @Test
    void shouldFailToCreateUserWithoutName(){
        AdminCreateUserDTO dto=AdminCreateUserDTO.builder()
                .fullName("")
                .email("email@gmail.com")
                .age(20)
                .role(Role.CLIENT)
                .password("Abc12345#sd")
                .build();
        Assertions.assertThrows(ConstraintViolationException.class, ()->service.createUserByAdmin(dto));

    }

    @Test
    void shouldAddNewRoleToUser(){
        AdminCreateUserDTO dto=AdminCreateUserDTO.builder()
                .fullName("Juan Antonio Chao Salinas")
                .email("email@gmail.com")
                .age(20)
                .role(Role.CLIENT)
                .password("Abc12345#sd")
                .build();
        User user= service.createUserByAdmin(dto);
        User updatedUser=service.addRole(Role.DRIVER,user.getId());

        Assertions.assertEquals(2,updatedUser.getRoles().size());

    }

    @Test
    void shouldFailAddExistingRoleToUser(){
        AdminCreateUserDTO dto=AdminCreateUserDTO.builder()
                .fullName("Juan Antonio Chao Salinas")
                .email("email@gmail.com")
                .age(20)
                .role(Role.CLIENT)
                .password("Abc12345#sd")
                .build();
        User user= service.createUserByAdmin(dto);


        Assertions.assertThrows(UserOperationException.class,()->service.addRole(Role.CLIENT,user.getId()));

    }
}
