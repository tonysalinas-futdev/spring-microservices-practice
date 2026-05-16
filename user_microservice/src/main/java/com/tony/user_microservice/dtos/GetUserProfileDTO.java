package com.tony.user_microservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetUserProfileDTO {
    private String fullName;
    private String email;
    private int age;
    private LocalDateTime registerAt;
}
