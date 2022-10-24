package com.trip.tripapplication.client.tomtom.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TomTomRoutesDto {

    private TomTomSummaryDto summary;

}
