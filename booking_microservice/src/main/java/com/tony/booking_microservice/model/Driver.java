package com.tony.booking_microservice.model;

import lombok.Data;

@Data
public class Driver {
    private Long id;
    private String fullName;
    private boolean available;
}
