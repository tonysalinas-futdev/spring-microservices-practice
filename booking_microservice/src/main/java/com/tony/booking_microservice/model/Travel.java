package com.tony.booking_microservice.model;

import com.tony.booking_microservice.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "travel")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long driverId;
  private Long clientId;
  private double originLat;
  private double originLon;
  private double destinationLat;
  private double destinationLon;

  @Enumerated(EnumType.STRING)
  private Status status;

  @CreationTimestamp private LocalDateTime createdAt;
  @UpdateTimestamp private LocalDateTime updatedAt;
}
