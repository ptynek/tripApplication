package com.trip.tripapplication.domain.dto;

import com.trip.tripapplication.domain.Cities;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteDto {

    private long lengthInMeters;
    private long travelTimeInSeconds;
    private long trafiicDelayInSeconds;
    private Cities cityFrom;
    private Cities cityTo;
}
