package com.tony.orchestrator_service.clients;

import com.tony.orchestrator_service.dtos.CreateTravelDTO;
import com.tony.orchestrator_service.entitys.Travel;
import com.tony.orchestrator_service.enums.TravelStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "booking-microservice")
public interface BookingMicroserviceClient {
    @GetMapping("/{id}")
    Travel getById(@PathVariable Long id);

    @PostMapping
     Travel createTravel(@RequestBody CreateTravelDTO dto);

    @PutMapping("/{id}/status")
    Travel updateStatus(@RequestBody TravelStatus status, @PathVariable Long id);


    @PutMapping("/{id}/driver/{driverId}")
    Travel updateDriver(@PathVariable Long driverId, @PathVariable Long id);
}
