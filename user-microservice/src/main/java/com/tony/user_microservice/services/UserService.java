package com.tony.user_microservice.services;
import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.dtos.CreateUserBySignUp;
import com.tony.user_microservice.dtos.GetUserProfileDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.exceptions.UserCreationError;
import com.tony.user_microservice.exceptions.UserOperationException;
import com.tony.user_microservice.model.Roles;
import com.tony.user_microservice.model.User;
import com.tony.user_microservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserService {
    private final UserRepository repo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public User getByEmailOrThrow(String email){
        return repo.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
    }

    public User createUserByAdmin(@Valid AdminCreateUserDTO dto){
        verifyExistingEmail(dto.getEmail());
        Roles role=roleService.getByNameOrThrow(dto.getRole());
        User user=User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        user.setRoles(Set.of(role));
        repo.saveAndFlush(user);

        return user;
    }

    public User createUserBySignUp(@Valid CreateUserBySignUp dto){
        verifyExistingEmail(dto.getEmail());
        Roles role=roleService.getByNameOrThrow(Role.CLIENT);
        User user= User.builder()
                .fullName(dto.getFullName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))

                .build();
        user.setRoles(Set.of(role));
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

    public User addRole(Role rol, Long userId){
        User user=getByIdOrThrow(userId);
        Roles role=roleService.getByNameOrThrow(rol);
        if (user.getRoles().stream().anyMatch(r->r.getName().equals(rol))){
            log.info("The user already have the role={}", rol.toString());
            throw new UserOperationException("The user with id '" + userId + "' already have the role '" +role.getName()+"'");
        }
        user.getRoles().add(role);
        repo.saveAndFlush(user);
        log.info("Adding the role={} to user with id={}",role.getName(),userId);
        return user;
    }

    public User deleteRole(Role rol, Long userId){
        User user=getByIdOrThrow(userId);
        roleService.getByNameOrThrow(rol);
        if (user.getRoles().stream().noneMatch(r->r.getName().equals(rol))){
            throw new UserOperationException("This user not have this role");
        }
        user.getRoles().removeIf(r->r.getName().equals(rol));
        repo.save(user);
        log.info("Deleting the role={} to user with id={}", rol.toString(),userId);
        return user;
    }

}
