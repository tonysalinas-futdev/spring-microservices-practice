package com.tony.booking_microservice.service;

import com.tony.booking_microservice.client.DriverClient;
import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.exceptions.NotFoundException;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import com.tony.booking_microservice.model.Driver;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.repository.TravelRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
  private final TravelRepository repo;


  @Override
  public List<Travel> getByStatus(Status status) {
    return repo.findByStatus(status);
  }

  @Transactional
  @Override
  public Travel createTravel(CreateTravelDTO travelDTO) {
    List<Travel> existingTravel =
        repo.findByStatusEqualsAndClientId(Status.IN_PROGRESS, travelDTO.getClientId());
    if (!existingTravel.isEmpty()) {
      throw new TravelCreationException("The client already has a travel in progress");
    }
    Travel travel =
        Travel.builder()
            .clientId(travelDTO.getClientId())
            .originLat(travelDTO.getOriginLat())
            .originLon(travelDTO.getOriginLon())
            .destinationLat(travelDTO.getDestinationLat())
            .destinationLon(travelDTO.getDestinationLon())
            .status(Status.CREATED)
            .build();
    repo.saveAndFlush(travel);
    return travel;
  }

  @Transactional
  @Override
  public Travel updateTravelDriver(Long driverId, Long travelId) {
    Travel travel = getByIdOrThrow(travelId);
    travel.setDriverId(driverId);
    repo.saveAndFlush(travel);
    updateTravelStatus(Status.IN_PROGRESS,travelId);
    return travel;
  }

  @Override
  public Travel updateTravelStatus(Status status, Long travelId) {
    Travel travel = getByIdOrThrow(travelId);
    travel.setStatus(status);
    repo.saveAndFlush(travel);
    return travel;
  }

  @Override
  public Travel getByIdOrThrow(Long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Travel not found"));
  }
}
