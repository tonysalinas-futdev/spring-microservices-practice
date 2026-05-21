package com.tony.booking_microservice.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {
  private final String message;
  private final String path;
  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int statusCode;
}
