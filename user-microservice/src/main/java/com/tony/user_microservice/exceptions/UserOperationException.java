package com.tony.user_microservice.exceptions;

public class UserOperationException extends RuntimeException{
    public UserOperationException(String message){
        super(message);
    }
}
