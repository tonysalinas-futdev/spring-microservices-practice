package com.tony.microservices.repository;

import com.tony.microservices.entitys.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestDriverRepository {
  @Autowired private DriverRepository repo;

  @Test
  void whenFindByName_thenReturnDriver() {
    Driver driver =
        Driver.builder().name("Test Driver").licenseNumber("23432546").available(true).build();

    repo.save(driver);

    Assertions.assertNotNull(repo.findByName("Test Driver"));
  }
}
