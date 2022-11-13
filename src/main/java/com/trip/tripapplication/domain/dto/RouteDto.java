package com.trip.tripapplication.domain.dto;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Passengers;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {

    private long id;
    private long lengthInMeters;
    private long travelTimeInSeconds;
    private long traficDelayInSeconds;
    private CitiesDto cityFrom;
    private CitiesDto cityTo;
    private PassengersDto passengersDto;
    private WeatherDto weatherDto;
    private LocalDateTime dateOfTrip;
    private BigDecimal price;

}
