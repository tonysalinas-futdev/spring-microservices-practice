package com.tony.orchestrator_service.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
public class Driver {

    private Long id;

    private String name;

    private String phone;

    private String licenseNumber;

    private boolean available;

    private double latitude;

    private double longitude;
}
