package com.tony.microservices.dto;

import com.tony.microservices.exceptions.BussinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateDriverDTO {
  private final Long userId;
  private final String phone;
  private final String licenseNumber;


}
