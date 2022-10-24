package com.trip.tripapplication.client.tomtom.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TomTomSummaryDto {

    private long lengthInMeters;
    private long travelTimeInSeconds;
    private long trafiicDelayInSeconds;

}
