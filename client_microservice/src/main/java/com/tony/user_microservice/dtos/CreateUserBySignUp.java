package com.tony.user_microservice.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateUserBySignUp {
    @NotBlank(message = "User name cannot be empty or blank")
    private final String fullName;

    @Email(message = "User email error")
    private final String email;

    private final int age;
    @NotNull(
            message =
                    "La contraseña debe tener al menos una letra mayúscula , una letra minúscula, un número y un caracter especial")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$")
    private final String password;
}
