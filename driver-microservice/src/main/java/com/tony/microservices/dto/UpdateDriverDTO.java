package com.tony.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateDriverDTO {
  private String name;
  private String phone;
  private String licenseNumber;
}
