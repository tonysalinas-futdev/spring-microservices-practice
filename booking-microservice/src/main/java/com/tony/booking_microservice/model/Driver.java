package com.tony.booking_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Driver {
    private Long id;
    private String fullName;
    private boolean available;
}
