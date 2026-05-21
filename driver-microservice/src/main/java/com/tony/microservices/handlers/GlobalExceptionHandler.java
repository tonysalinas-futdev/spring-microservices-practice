package com.tony.microservices.handlers;

import com.tony.microservices.dto.ErrorResponseDTO;
import com.tony.microservices.exceptions.BussinessException;
import com.tony.microservices.exceptions.NotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BussinessException.class)
  public ErrorResponseDTO handleBussinessException(
      HttpServletRequest request, BussinessException ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 400);
  }

  @ExceptionHandler(NotFoundExceptions.class)
  public ErrorResponseDTO handleNotFoundException(
      HttpServletRequest request, NotFoundExceptions ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 404);
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponseDTO handleException(HttpServletRequest request, Exception ex) {
    return new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 500);
  }
}
