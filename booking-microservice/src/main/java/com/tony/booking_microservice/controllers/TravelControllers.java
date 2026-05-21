package com.tony.booking_microservice.controllers;

import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.service.TravelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/travels")
@RequiredArgsConstructor
public class TravelControllers {
  private final TravelService service;

  @GetMapping("/{status}")
  public ResponseEntity<List<Travel>> getByStatus(@PathVariable Status status) {
    return ResponseEntity.ok(service.getByStatus(status));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Travel> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getByIdOrThrow(id));
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Travel> createTravel(@RequestBody CreateTravelDTO dto) {
    return ResponseEntity.ofNullable(service.createTravel(dto));
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<Travel> updateStatus(@RequestBody Status status, @PathVariable Long id) {
    return ResponseEntity.ok(service.updateTravelStatus(status, id));
  }

  @PutMapping("/{id}/driver/{driverId}")
  public ResponseEntity<Travel> updateDriver(@PathVariable Long driverId, @PathVariable Long id) {
    return ResponseEntity.ok(service.updateTravelDriver(driverId, id));
  }
}
