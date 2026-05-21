package com.tony.booking_microservice.dtos;

import com.tony.booking_microservice.exceptions.InvalidDataException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTravelDTO {
  private final Long clientId;
  private final double originLat;
  private final double originLon;
  private final double destinationLat;
  private final double destinationLon;

  public CreateTravelDTO(
      Long clientId,
      double originLat,
      double originLon,
      double destinationLat,
      double destinationLon) {
    if (clientId == null) {
      throw new InvalidDataException("The client id cannot be null");
    }
    if (!isValidCoordinates(originLat, originLon)
        && !isValidCoordinates(destinationLat, destinationLon)) {
      throw new InvalidDataException(
          "Latitude must be between -90 and 90 and longitude between -180 and 180");
    }
    this.clientId = clientId;
    this.originLat = originLat;
    this.originLon = originLon;
    this.destinationLat = destinationLat;
    this.destinationLon = destinationLon;
  }

  public Boolean isValidCoordinates(double latitude, double longitude) {
    return latitude >= -90 && latitude <= 90 && longitude <= 180 && longitude >= -180;
  }
}
