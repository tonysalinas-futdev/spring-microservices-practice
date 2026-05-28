package com.tony.booking_microservice.exceptions;

public class TravelUpdateException extends RuntimeException{
    public TravelUpdateException(String message){
        super(message);
    }
}
