package com.trip.tripapplication.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteDto {

    private long lengthInMeters;
    private long travelTimeInSeconds;
    private long trafiicDelayInSeconds;
    private double latFrom;
    private double lonFrom;
    private double latTo;
    private double lonTo;
}
