package com.trip.tripapplication.client.tomtom.dto;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TomTomSummaryDto {

    private long lengthInMeters;
    private long travelTimeInSeconds;
    private long trafiicDelayInSeconds;

}
