package com.tony.booking_microservice.exceptions;

public class TravelCreationException extends RuntimeException {
  public TravelCreationException(String message) {
    super(message);
  }
}
