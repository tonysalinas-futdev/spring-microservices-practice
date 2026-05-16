package com.tony.user_microservice.exceptions;

public class UserCreationError extends RuntimeException{
    public UserCreationError(String message){
        super(message);
    }
}
