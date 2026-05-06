package com.tony.microservices.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {
  private String message;
  private String path;
  private final LocalDateTime timestamp = LocalDateTime.now();
  private int statusCode;
}
