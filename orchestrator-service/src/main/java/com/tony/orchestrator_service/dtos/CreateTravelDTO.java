package com.tony.orchestrator_service.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class CreateTravelDTO {

    private final Long clientId;
    private final double originLat;
    private final double originLon;
    private final double destinationLat;
    private final double destinationLon;

}
