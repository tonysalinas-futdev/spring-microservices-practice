package com.tony.microservices.dto;

import com.tony.microservices.exceptions.BussinessException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDriverDTO {
  private final String name;
  private final String phone;
  private final String licenseNumber;

  public CreateDriverDTO(String name, String phone, String licenseNumber) {
    if (name == null || name.isBlank()) {
      throw new BussinessException("The name of the driver cannot be null or empty");
    }
    this.name = name;
    this.phone = phone;
    this.licenseNumber = licenseNumber;
  }
}
