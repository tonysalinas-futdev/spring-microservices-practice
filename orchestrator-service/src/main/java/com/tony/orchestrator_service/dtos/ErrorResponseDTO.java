package com.tony.orchestrator_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponseDTO {
    private final String message;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
}
