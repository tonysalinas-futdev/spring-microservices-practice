package com.tony.booking_microservice.service;

import com.tony.booking_microservice.feign.DriverClient;
import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.exceptions.NotFoundException;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import com.tony.booking_microservice.feign.service.FeignDriverClientService;
import com.tony.booking_microservice.model.Driver;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.repository.TravelRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
  private final TravelRepository repo;
    private final FeignDriverClientService feignDriverService;

  @Override
  public List<Travel> getByStatus(Status status) {
    return repo.findByStatus(status);
  }

  @Transactional
  @Override
  public Travel createTravel(CreateTravelDTO travelDTO, String clientId) {
    List<Travel> existingTravel =
        repo.findByStatusEqualsAndClientId(Status.IN_PROGRESS, Long.valueOf(clientId));
    if (!existingTravel.isEmpty()) {
      throw new TravelCreationException("The client already has a travel in progress");
    }
    Travel travel =
        Travel.builder()
            .clientId(Long.valueOf(clientId))
            .originLat(travelDTO.getOriginLat())
            .originLon(travelDTO.getOriginLon())
            .destinationLat(travelDTO.getDestinationLat())
            .destinationLon(travelDTO.getDestinationLon())
            .status(Status.CREATED)
            .build();
    repo.saveAndFlush(travel);
    log.info("Created travel for client with id={}",clientId);
    return travel;
  }

  @Transactional
  @Override
  public Travel updateTravelDriver(Long driverId, Long travelId) {
      Driver driver=feignDriverService.getBydId(driverId);
    Travel travel = getByIdOrThrow(travelId);
    travel.setDriverId(driverId);
    feignDriverService.updateDriverAvaiability(driver.getId(),false);
    repo.saveAndFlush(travel);
    updateTravelStatus(Status.IN_PROGRESS,travelId);
    log.info("Assignated driver with id={} for travel with id={}",driverId,travelId);
    return travel;
  }

  @Override
  public Travel updateTravelStatus(Status status, Long travelId) {

    Travel travel = getByIdOrThrow(travelId);
    travel.setStatus(status);
    repo.saveAndFlush(travel);
    log.info("Status changed to {} for travel with id={}",status.toString(),travelId);
    return travel;
  }

  @Override
  public Travel getByIdOrThrow(Long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Travel not found"));
  }
}
