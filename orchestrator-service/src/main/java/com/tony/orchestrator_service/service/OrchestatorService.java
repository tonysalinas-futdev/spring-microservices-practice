package com.tony.orchestrator_service.service;

import com.tony.orchestrator_service.clients.BookingMicroserviceClient;
import com.tony.orchestrator_service.clients.DriverMicroserviceClient;
import com.tony.orchestrator_service.dtos.CreateTravelDTO;
import com.tony.orchestrator_service.dtos.ErrorResponseDTO;
import com.tony.orchestrator_service.entitys.Driver;
import com.tony.orchestrator_service.entitys.Travel;
import com.tony.orchestrator_service.enums.TravelStatus;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrchestatorService {


    private DriverMicroserviceClient driverClient;
    private BookingMicroserviceClient bookingClient;

    public void assignDriverToTravel(Long driverId,Long travelId){

            Driver driver = driverClient.getById(driverId);
            Travel travel=bookingClient.getById(travelId);

            try {
                bookingClient.updateDriver(driver.getId(),travel.getId());
                bookingClient.updateStatus(TravelStatus.IN_PROGRESS,travelId);
                driverClient.updateDriverStatus(driverId,false);
                log.info("Realizado exitosamente el workflow para asignar un conductor al travel con id={}, driver={}",travelId,driverId);
            } catch (FeignException e) {
                ErrorResponseDTO response=

            }


    }
}
