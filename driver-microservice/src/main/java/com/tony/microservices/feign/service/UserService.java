package com.tony.microservices.feign.service;

import com.tony.microservices.exceptions.DriverCreationException;
import com.tony.microservices.feign.client.UserClient;
import com.tony.microservices.feign.models.Role;
import com.tony.microservices.feign.models.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient client;

    @CircuitBreaker(name = "user-microservice", fallbackMethod = "getByIdCircuitBreakerFallbackMethod")
    @Retry(name = "user-microservice" )
    public User getById(Long userId){
        log.info("Attempting to fetch user by id from the user microservice");
        User user=client.getUserById(userId).getBody();
        log.info("User with id={} fetched successfully",userId);
        return user;
    }
    public User getByIdCircuitBreakerFallbackMethod(Long userId, Throwable ex){
        log.error("Failed to fetching user from user-microservice",ex);
        throw new DriverCreationException("Failed to create driver");
    }



    @CircuitBreaker(name = "user-microservice", fallbackMethod = "addDriverRoleCircuitBreakerFallbackMethod")
    public User addDriverRole(Role role, Long userId){
        log.info("Attempting to add Driver role to user with id={}");
        User user=client.addNewUserRole(Role.DRIVER,userId).getBody();
        log.info("Operation Completed successfully");
        return user;

    }
    public User addDriverRoleCircuitBreakerFallbackMethod(Role role, Long userId, Throwable ex){
        log.error("Failed to update the role of the user with id={} from user-microservice",ex, userId);
        throw new DriverCreationException("Failed to create driver");
    }
}
