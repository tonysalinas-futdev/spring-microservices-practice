package com.tony.microservices.services;

import com.tony.microservices.dto.CreateDriverDTO;
import com.tony.microservices.dto.UpdateDriverDTO;
import com.tony.microservices.entitys.Driver;
import java.util.List;

public interface DriverService {

  Driver createDriver(CreateDriverDTO data);

  List<Driver> getAllDrivers();

  void deleteDriver(Long id);

  Driver updateDriver(UpdateDriverDTO data, Long id);

  void changeAvaiability(Boolean newStatus, Long id);

  Driver getByIdOrThrow(Long id);
}
