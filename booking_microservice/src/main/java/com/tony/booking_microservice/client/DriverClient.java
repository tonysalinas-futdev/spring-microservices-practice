package com.tony.booking_microservice.client;

import com.tony.booking_microservice.model.Driver;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@Service
@FeignClient("driver-microservice")
@RequestMapping("api/v1/drivers")
public interface DriverClient {
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getById(@PathVariable Long id);

    @PutMapping("driver/{id}/status")
    public void updateDriverStatus(@PathVariable Long id, Boolean status);


}
