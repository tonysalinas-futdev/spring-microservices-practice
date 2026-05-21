package com.tony.booking_microservice.repository;

import com.tony.booking_microservice.enums.Status;
import com.tony.booking_microservice.model.Travel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
  List<Travel> findByStatusEqualsAndClientId(Status status, Long clientId);

  List<Travel> findByStatus(Status status);

  List<Travel> findByClientId(Long clientId);

}
