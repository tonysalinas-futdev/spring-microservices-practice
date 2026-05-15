package com.tony.user_microservice.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
  private String email;
  private String password;
}
