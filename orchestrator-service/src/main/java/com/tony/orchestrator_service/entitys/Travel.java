package com.tony.orchestrator_service.entitys;

import com.tony.orchestrator_service.enums.TravelStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Travel {

    private Long id;
    private Long driverId;
    private Long clientId;
    private double originLat;
    private double originLon;
    private double destinationLat;
    private double destinationLon;

    private TravelStatus status;

     private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
