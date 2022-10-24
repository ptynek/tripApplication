package com.trip.tripapplication.client.tomtom.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TomTomMain {

    private List<TomTomRoutesDto> routes;
}
