package com.tony.microservices.exceptions;

public class BussinessException extends RuntimeException {
  public BussinessException(String message) {
    super(message);
  }
}
