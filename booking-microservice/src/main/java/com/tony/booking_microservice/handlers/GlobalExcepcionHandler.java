package com.tony.booking_microservice.handlers;

import com.tony.booking_microservice.dtos.ErrorResponseDTO;
import com.tony.booking_microservice.exceptions.NotFoundException;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepcionHandler {

  @ExceptionHandler(exception = NotFoundException.class)
  public ErrorResponseDTO handleNotFoundException(
      HttpServletRequest request, NotFoundException ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 404);
  }

  @ExceptionHandler(exception = TravelCreationException.class)
  public ErrorResponseDTO handleTravelCreationException(
      HttpServletRequest request, TravelCreationException ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 400);
  }

  @ExceptionHandler(exception = Exception.class)
  public ErrorResponseDTO handleException(HttpServletRequest request, Exception ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 500);
  }
}
