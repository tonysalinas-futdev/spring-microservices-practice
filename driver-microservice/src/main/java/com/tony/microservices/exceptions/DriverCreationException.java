package com.tony.microservices.exceptions;

public class DriverCreationException extends RuntimeException{
    public DriverCreationException(String message){
        super(message);
    }
}
