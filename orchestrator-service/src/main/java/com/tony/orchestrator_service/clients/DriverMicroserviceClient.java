package com.tony.orchestrator_service.clients;

import com.tony.orchestrator_service.entitys.Driver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "driver-microservice")
public interface DriverMicroserviceClient {


        @GetMapping("/{id}")
        Driver getById(@PathVariable Long id);

        @PutMapping("driver/{id}/status")
        void updateDriverStatus(@PathVariable Long id, @RequestBody Boolean status);


    }


