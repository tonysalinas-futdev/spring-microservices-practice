package com.tony.user_microservice.handler;

import com.tony.user_microservice.dtos.ErrorResponseDTO;
import com.tony.user_microservice.exceptions.NotFoundException;
import com.tony.user_microservice.exceptions.UserCreationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDTO handleNotFoundException(NotFoundException ex, HttpServletRequest request,HttpServletResponse response){
        response.setStatus(404);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),404);
    }

    @ExceptionHandler(UserCreationError.class)
    public ErrorResponseDTO handleUserCreationError(UserCreationError ex, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(400);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),400);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDTO handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request,HttpServletResponse response){
        response.setStatus(400);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),400);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponseDTO handleAuthenticationException(AuthenticationException ex, HttpServletRequest request,HttpServletResponse response){
        response.setStatus(401);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),401);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponseDTO handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request,HttpServletResponse response){
        response.setStatus(403);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),403);
    }


    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleException(Exception ex, HttpServletRequest request,HttpServletResponse response){
        response.setStatus(500);
        return new ErrorResponseDTO(request.getRequestURI(),ex.getMessage(),500);
    }
}
