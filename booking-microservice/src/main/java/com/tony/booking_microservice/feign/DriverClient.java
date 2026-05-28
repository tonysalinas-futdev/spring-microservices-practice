package com.tony.booking_microservice.feign;

import com.tony.booking_microservice.model.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "driver-microservice")
public interface DriverClient {
    @GetMapping("/api/v1/drivers/{id}")
    public Driver getById(@PathVariable Long id);

    @PutMapping("api/v1/drivers/driver/{id}/status")
    public void updateDriverAvaibility(@PathVariable Long id, @RequestBody Boolean status);


}
