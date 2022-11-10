package com.trip.tripapplication.domain.dto;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Passengers;
import lombok.*;

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

    public RouteDto(long lengthInMeters, long travelTimeInSeconds, long traficDelayInSeconds, CitiesDto cityFrom, CitiesDto cityTo) {
        this.lengthInMeters = lengthInMeters;
        this.travelTimeInSeconds = travelTimeInSeconds;
        this.traficDelayInSeconds = traficDelayInSeconds;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }
}
