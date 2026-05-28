package com.tony.booking_microservice.events.handlers;

import com.tony.booking_microservice.events.UpdateTravelDriverEvent;
import com.tony.booking_microservice.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateTravelDriverHandler {
    private final TravelService travelService;


    @EventListener
    public void handle(UpdateTravelDriverEvent event){

    }
}
