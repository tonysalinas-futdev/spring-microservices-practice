package com.tony.microservices.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  private String phone;

  @Column(name = "license_number")
  private String licenseNumber;

  private boolean available;

  private double latitude;

  private double longitude;
}
