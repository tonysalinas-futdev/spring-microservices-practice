package com.tony.booking_microservice.exceptions;

public class InvalidDataException extends RuntimeException {
  public InvalidDataException(String message) {
    super(message);
  }
}
