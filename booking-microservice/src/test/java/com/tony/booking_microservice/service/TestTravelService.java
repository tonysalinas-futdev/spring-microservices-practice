package com.tony.booking_microservice.service;

import com.tony.booking_microservice.dtos.CreateTravelDTO;
import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.exceptions.TravelCreationException;
import com.tony.booking_microservice.feign.DriverClient;
import com.tony.booking_microservice.feign.service.FeignDriverClientService;
import com.tony.booking_microservice.model.Driver;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.repository.TravelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import static org.mockito.Mockito.*;


@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestTravelService {
  @Autowired private TravelService service;
  @Autowired private TravelRepository repo;

  @MockitoBean
  private DriverClient driverClient;

  CreateTravelDTO getTravelForTests() {
    return CreateTravelDTO.builder()

        .destinationLat(40.0000)
        .destinationLon(100.0000)
        .originLat(39.0000)
        .originLon(99.0000)
        .build();
  }

  @Test
  void shouldCreateTravelSuccessfully() {
    CreateTravelDTO dto = getTravelForTests();
    Travel travel = service.createTravel(dto,"4");

    Assertions.assertEquals(1, repo.findByStatusEqualsAndClientId(Status.CREATED, 4L).size());
  }

  @Test
  void shouldFailToCreateTravelWithAnotherInProgress() {
    Travel travel = service.createTravel(getTravelForTests(),"4");
    service.updateTravelStatus(Status.IN_PROGRESS, travel.getId());

    Assertions.assertThrows(
        TravelCreationException.class, () -> service.createTravel(getTravelForTests(),"4"));
  }

  @Test
  void shouldUpdateTravelDriverId() {
      when(driverClient.getById(10L)).thenReturn(new Driver(4L, "Test Driver",true));
     doNothing().when(driverClient).updateDriverAvaibility(4L,false);
    Travel travel = service.createTravel(getTravelForTests(),"4");

    service.updateTravelDriver(10L, travel.getId());

    Travel updatedTravel = service.getByIdOrThrow(travel.getId());
    Assertions.assertEquals(10L, updatedTravel.getDriverId());
  }
}
