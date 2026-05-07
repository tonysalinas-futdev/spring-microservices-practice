package com.tony.booking_microservice.service;

import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.repository.TravelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestTravelService {
  @Autowired private TravelService service;
  @Autowired private TravelRepository repo;

  CreateTravelDTO getTravelForTests() {
    return CreateTravelDTO.builder()
        .clientId(4L)
        .destinationLat(40.0000)
        .destinationLon(100.0000)
        .originLat(39.0000)
        .originLon(99.0000)
        .build();
  }

  @Test
  void shouldCreateTravelSuccessfully() {
    CreateTravelDTO dto = getTravelForTests();
    Travel travel = service.createTravel(dto);

    Assertions.assertEquals(1, repo.findByStatusEqualsAndClientId(Status.CREATED, 4L).size());
  }

  @Test
  void shouldFailToCreateTravelWithAnotherInProgress() {
    Travel travel = service.createTravel(getTravelForTests());
    service.updateTravelStatus(Status.IN_PROGRESS, travel.getId());

    Assertions.assertThrows(
        TravelCreationException.class, () -> service.createTravel(getTravelForTests()));
  }

  @Test
  void shouldUpdateTravelDriverId() {
    Travel travel = service.createTravel(getTravelForTests());

    service.updateTravelDriver(10L, travel.getId());

    Travel updatedTravel = service.getByIdOrThrow(travel.getId());
    Assertions.assertEquals(10L, updatedTravel.getDriverId());
  }
}
