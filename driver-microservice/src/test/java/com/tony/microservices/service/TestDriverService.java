package com.tony.microservices.service;

import static org.mockito.Mockito.when;

import com.tony.microservices.dto.CreateDriverDTO;
import com.tony.microservices.entitys.Driver;
import com.tony.microservices.exceptions.BussinessException;
import com.tony.microservices.repository.DriverRepository;
import com.tony.microservices.services.DriverService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestDriverService {
  @MockitoSpyBean private DriverRepository repo;

  @Autowired private DriverService service;

  @BeforeAll
  static void setup() {}

  Driver buildClientForTests() {
    return Driver.builder()
        .id(1L)
        .name("Driver")
        .phone("53000748")
        .licenseNumber("123456789")
        .available(true)
        .build();
  }

  @Test
  void shouldThrowBussinessExceptionWhenCreateDriverWithExistingName() {
    Driver driver =
        service.createDriver(new CreateDriverDTO("Driver Test", "52000748", "123213234"));
    Assertions.assertThrows(
        BussinessException.class,
        () -> service.createDriver(new CreateDriverDTO("Driver Test", "52000695", "4546")));
  }

  @Test
  void shouldCreateDriverSuccessfully() {
    Driver driver = service.createDriver(new CreateDriverDTO("Driver", "52000748", "4654652452"));

    Assertions.assertNotNull(repo.findByName("Tony"));
  }

  @Test
  void whenGetByIdThenReturnClientSuccessfully() {
    when(repo.findById(1L)).thenReturn(Optional.of(buildClientForTests()));

    Assertions.assertNotNull(service.getByIdOrThrow(1L));
  }

  @Test
  void whenChangeAvaiabilityThenUpdateDriverStatus() {
    Driver driver = service.createDriver(new CreateDriverDTO("Driver", "52000748", "12312"));

    service.changeAvaiability(false, driver.getId());
    Driver updatedDriver = service.getByIdOrThrow(driver.getId());

    Assertions.assertFalse(updatedDriver.isAvailable());
  }
}
