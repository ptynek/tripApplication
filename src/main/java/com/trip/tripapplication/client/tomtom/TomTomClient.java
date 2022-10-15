package com.trip.tripapplication.client.tomtom;

import com.trip.tripapplication.client.tomtom.dto.TomTomMain;
import com.trip.tripapplication.domain.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class TomTomClient {

    private final RestTemplate restTemplate;

    @Value("${tomtom.api.endpoint}")
    private String endpoint;
    @Value("${tomtom.api.key}")
    private String apiKey;

    public RouteDto getRoute(double latFrom, double lonFrom, double latTo, double lonTo){
        TomTomMain routes = restTemplate.getForObject(endpoint + "{latFrom},{lonFrom}:{latTo},{lonTo}/" +
                "json?instructionsType=text&language=en-US&vehicleHeading=90&sectionType=traffic&report=effectiveSettings&routeType=eco&traffic=true&avoid=unpavedRoads&travelMode=bus" +
                "&vehicleMaxSpeed=120&vehicleEngineType=combustion&key=" + apiKey, TomTomMain.class, latFrom, lonFrom, latTo, lonTo);

        return RouteDto.builder()
                .travelTimeInSeconds(routes.getRoutes().get(0).getSummary().getTravelTimeInSeconds())
                .lengthInMeters(routes.getRoutes().get(0).getSummary().getLengthInMeters())
                .trafiicDelayInSeconds(routes.getRoutes().get(0).getSummary().getTrafiicDelayInSeconds())
                .latFrom(latFrom)
                .lonFrom(lonFrom)
                .latTo(latTo)
                .lonTo(lonTo)
                .build();
    }


}
