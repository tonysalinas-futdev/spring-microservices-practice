package com.tony.booking_microservice.feign.service;

import com.tony.booking_microservice.exceptions.TravelUpdateException;
import com.tony.booking_microservice.feign.DriverClient;
import com.tony.booking_microservice.model.Driver;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeignDriverClientService {
    private final DriverClient client;

    @CircuitBreaker(name = "driver-microservice", fallbackMethod = "getByIdFallbackMethod")
    @TimeLimiter(name = "driver-microservice",fallbackMethod = "fallbackGetByIdTimeLimiterMethod")
    @Retry(name = "driver-microservice")
    public Driver getBydId(Long id){
        Driver driver=client.getById(id);
        log.info("Fetched the driver with id={}", id);
        return driver;
    }

    public Driver getByIdFallbackMethod(Long id, Throwable ex){
        log.error("Feign Client error",ex);
        throw new TravelUpdateException("Communication error with the driver microservice");
    }

    public Driver fallbackGetByIdTimeLimiterMethod(Long id ,Throwable ex){
        log.error("Feign Client error",ex);
        throw  new RuntimeException("Service unavailaible");

    }

    @CircuitBreaker(name = "driver-microservice", fallbackMethod = "updateDriverAvaibilityFallbackMethod")
    public void updateDriverAvaiability(Long id, Boolean newStatus){
        client.updateDriverAvaibility(id,newStatus);
        log.info("Changed the avaiability of the driver with id={}, to '{}'", id, newStatus);

    }

    public void updateDriverAvaibilityFallbackMethod(Long id, Boolean newStatus,Throwable ex){
        log.error("Feign Client error",ex);
        throw new TravelUpdateException("Communication error with the driver microservice");

    }

}
