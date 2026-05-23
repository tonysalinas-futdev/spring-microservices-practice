package com.tony.booking_microservice.handlers;

import com.tony.booking_microservice.dtos.ErrorResponseDTO;
import com.tony.booking_microservice.exceptions.NotFoundException;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepcionHandler {

  @ExceptionHandler(exception = NotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleNotFoundException(
      HttpServletRequest request, NotFoundException ex) {
    return ResponseEntity.status(404).body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 404));
  }

  @ExceptionHandler(exception = TravelCreationException.class)
  public ResponseEntity<ErrorResponseDTO> handleTravelCreationException(
      HttpServletRequest request, TravelCreationException ex) {
      return ResponseEntity.status(404).body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 400));

  }

  @ExceptionHandler(exception = Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleException(HttpServletRequest request, Exception ex) {
      return ResponseEntity.status(404).body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 500));

  }
}
