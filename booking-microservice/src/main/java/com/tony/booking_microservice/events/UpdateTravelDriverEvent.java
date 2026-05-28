package com.tony.booking_microservice.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateTravelDriverEvent {
    private final Long travelId;
    private final String driverId;
}
