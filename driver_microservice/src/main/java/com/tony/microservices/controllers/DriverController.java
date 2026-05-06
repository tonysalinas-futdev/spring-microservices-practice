package com.tony.microservices.controllers;

import com.tony.microservices.dto.CreateDriverDTO;
import com.tony.microservices.dto.UpdateDriverDTO;
import com.tony.microservices.entitys.Driver;
import com.tony.microservices.services.DriverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {
  private final DriverService service;

  @GetMapping("/{id}")
  public ResponseEntity<Driver> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getByIdOrThrow(id));
  }

  @PostMapping()
  public ResponseEntity<Driver> createDriver(@RequestBody CreateDriverDTO data) {
    return ResponseEntity.status(201).body(service.createDriver(data));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Driver> updateDriver(
      @RequestBody UpdateDriverDTO data, @PathVariable Long id) {
    return ResponseEntity.ok(service.updateDriver(data, id));
  }

  @GetMapping
  public ResponseEntity<List<Driver>> getAllDrivers() {
    return ResponseEntity.ok(service.getAllDrivers());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDriver(@PathVariable Long id) {
    service.deleteDriver(id);
  }

  @PutMapping("driver/{id}/status")
  @ResponseStatus(HttpStatus.OK)
  public void updateDriverStatus(@PathVariable Long id, Boolean status) {
    service.changeAvaiability(status, id);
  }
}
