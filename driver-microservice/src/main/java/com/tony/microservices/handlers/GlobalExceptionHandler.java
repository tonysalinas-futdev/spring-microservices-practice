package com.tony.microservices.handlers;

import com.tony.microservices.dto.ErrorResponseDTO;
import com.tony.microservices.exceptions.BussinessException;
import com.tony.microservices.exceptions.NotFoundExceptions;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BussinessException.class)
  public ResponseEntity<ErrorResponseDTO> handleBussinessException(
      HttpServletRequest request, BussinessException ex) {
    return ResponseEntity.badRequest().body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 400));
  }

  @ExceptionHandler(NotFoundExceptions.class)
  public ResponseEntity<ErrorResponseDTO> handleNotFoundException(
      HttpServletRequest request, NotFoundExceptions ex) {
      return ResponseEntity.status(404).body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 404));
  }



  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleException(HttpServletRequest request, Exception ex) {
      if (ex instanceof AccessDeniedException){
          throw  (AccessDeniedException) ex;
      }
      if (ex instanceof AuthenticationException){
          throw  (AuthenticationException) ex;
      }
    return ResponseEntity.status(500).body(new ErrorResponseDTO(ex.getMessage(), request.getRequestURI(), 500));
  }
}
