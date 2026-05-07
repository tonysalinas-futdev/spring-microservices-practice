package com.tony.booking_microservice.service;

import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.model.Travel;
import java.util.List;

public interface TravelService {
  public List<Travel> getByStatus(Status status);

  public Travel createTravel(CreateTravelDTO travelDTO);

  public Travel updateTravelDriver(Long driverId, Long travelId);

  public Travel updateTravelStatus(Status status, Long travelId);

  public Travel getByIdOrThrow(Long id);
}
