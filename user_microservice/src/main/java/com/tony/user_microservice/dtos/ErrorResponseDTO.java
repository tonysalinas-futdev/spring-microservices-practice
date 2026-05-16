package com.tony.user_microservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorResponseDTO {
    private final String path;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp=LocalDateTime.now();
}
