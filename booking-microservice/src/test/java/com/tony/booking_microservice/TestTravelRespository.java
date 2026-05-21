package com.tony.booking_microservice;

import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.model.Travel;
import com.tony.booking_microservice.repository.TravelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestTravelRespository {
  @Autowired private TravelRepository repo;

  Travel getTravelForTests() {
    return Travel.builder()
        .clientId(4L)
        .destinationLat(45.3435)
        .destinationLon(100.1231)
        .originLat(45.0000)
        .originLon(99.1234)
        .status(Status.CREATED)
        .build();
  }

  @Test
  void shouldGetTravelByStatusAndClientSuccessfully() {
    Travel travel = repo.save(getTravelForTests());

    Assertions.assertNotNull(repo.findByStatusEqualsAndClientId(Status.CREATED, 4L));
  }
}
