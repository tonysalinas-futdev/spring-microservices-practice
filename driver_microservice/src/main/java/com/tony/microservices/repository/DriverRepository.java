package com.tony.microservices.repository;

import com.tony.microservices.entitys.Driver;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
  Optional<Driver> findByName(String name);
}
