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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
  private final DriverRepository repo;

  @Transactional
  @Override
  public Driver createDriver(CreateDriverDTO data) {
    if (repo.findByName(data.getName()).isPresent()) {
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
    return driver;
  }

  @Override
  public void changeAvaiability(Boolean newStatus, Long id) {
    Driver driver = getByIdOrThrow(id);
    driver.setAvailable(newStatus);
    repo.save(driver);
  }

  @Override
  public Driver getByIdOrThrow(Long id) {
    Optional<Driver> driver = repo.findById(id);
    if (driver.isEmpty()) {
      throw new NotFoundExceptions("Driver with id=" + id + " not found");
    }
    return driver.get();
  }
}
