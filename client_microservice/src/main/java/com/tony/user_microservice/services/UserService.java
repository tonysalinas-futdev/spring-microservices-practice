package com.tony.user_microservice.services;
import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.dtos.CreateUserBySignUp;
import com.tony.user_microservice.dtos.GetUserProfileDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.exceptions.UserCreationError;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository repo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public User getByEmailOrThrow(String email){
        return repo.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
    }

    public User createUserByAdmin(@Valid AdminCreateUserDTO dto){
        verifyExistingEmail(dto.getEmail());
        User user=User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .rol(roleService.getByNameOrThrow(dto.getRole()))
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        repo.saveAndFlush(user);

        return user;
    }

    public User createUserBySignUp(@Valid CreateUserBySignUp dto){
        verifyExistingEmail(dto.getEmail());
        User user= User.builder()
                .fullName(dto.getFullName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .rol(roleService.getByNameOrThrow(Role.CLIENT))
                .build();
        repo.saveAndFlush(user);
        return user;
    }


    public void verifyExistingEmail(String email){
        if (repo.existsByEmail(email)){
            throw new UserCreationError("User with email '"+ email +"' already exists");
        }
    }
    public List<User> getAllUsers(){
        return repo.findAll();

    }
    public void deleteUser(Long id){
        User user= getByIdOrThrow(id);
        repo.delete(user);
    }

    public User getByIdOrThrow(Long id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("User not found"));
    }

    public GetUserProfileDTO getProfile(String email){
        User user=getByEmailOrThrow(email);
        return new GetUserProfileDTO(user.getFullName(),user.getEmail(),user.getAge(),user.getRegisterAt());
    }
}
