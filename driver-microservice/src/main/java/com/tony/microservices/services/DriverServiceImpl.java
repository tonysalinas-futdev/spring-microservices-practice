package com.tony.microservices.services;

import com.tony.microservices.dto.CreateDriverDTO;
import com.tony.microservices.dto.UpdateDriverDTO;
import com.tony.microservices.entitys.Driver;
import com.tony.microservices.exceptions.BussinessException;
import com.tony.microservices.exceptions.NotFoundExceptions;
import com.tony.microservices.repository.DriverRepository;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {
  private final DriverRepository repo;

  @Transactional
  @Override
  public Driver registerAsDriver(Long userId){

  };

  @Transactional
  @Override
  public Driver createDriver(CreateDriverDTO data) {
    if (repo.findByName(data.getName()).isPresent()) {
        log.info("Failed to create driver with existing name");
      throw new BussinessException("Client with name '" + data.getName() + "' already exists");

    }
    Driver driver =
        Driver.builder()
            .name(data.getName())
            .licenseNumber(data.getLicenseNumber())
            .available(false)
            .phone(data.getPhone())
            .build();
    repo.saveAndFlush(driver);
    log.info("Created driver with id={} and name={}",driver.getId(),driver.getName());
    return driver;
  }

  @Override
  public List<Driver> getAllDrivers() {
    return repo.findAll();
  }

  @Override
  public void deleteDriver(Long id) {
    Driver driver = getByIdOrThrow(id);
    repo.delete(driver);
    log.info("Deleted driver with id={}",id);
  }

  @Transactional
  @Override
  public Driver updateDriver(UpdateDriverDTO data, Long id) {
    Driver driver = getByIdOrThrow(id);

    if (data.getName() != null) {
      if (repo.findByName(data.getName()).isPresent()) {
        throw new BussinessException("Client with name '" + data.getName() + "' already exists");
      }
      driver.setName(data.getName());
    }
    if (data.getPhone() != null) {
      driver.setPhone(data.getPhone());
    }
    if (data.getLicenseNumber() != null) {
      driver.setLicenseNumber(data.getLicenseNumber());
    }
    repo.saveAndFlush(driver);
    log.info("Updated driver with id={}",id);
    return driver;
  }

  @Override
  public void changeAvaiability(Boolean newStatus, Long id) {
    Driver driver = getByIdOrThrow(id);
    driver.setAvailable(newStatus);
    repo.save(driver);
    log.info("Changed the avaiability of driver with id={}",id);
  }

  @Override
  public Driver getByIdOrThrow(Long id) {
    Optional<Driver> driver = repo.findById(id);
    if (driver.isEmpty()) {
        log.info("Driver with id={}, not found",id);
      throw new NotFoundExceptions("Driver with id=" + id + " not found");
    }
    log.info("Found the driver with id={}",id);
    return driver.get();
  }
}
