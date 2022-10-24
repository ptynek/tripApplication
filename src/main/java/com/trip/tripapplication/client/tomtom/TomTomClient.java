package com.trip.tripapplication.client.tomtom;

import com.trip.tripapplication.client.tomtom.dto.TomTomMain;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.dto.CitiesDto;
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
    private double latFrom;
    private double lonFrom;
    private double latTo;
    private double lonTo;

    public RouteDto getRoute(CitiesDto cityFrom, CitiesDto cityTo){
        latFrom = cityFrom.getLatitude();
        lonFrom = cityFrom.getLongitude();
        latTo = cityTo.getLatitude();
        lonTo = cityTo.getLongitude();

        TomTomMain routes = restTemplate.getForObject(endpoint + latFrom +"," + lonFrom + ":" + latTo +"," + lonTo + "/" +
                "json?instructionsType=text&language=en-US&vehicleHeading=90&sectionType=traffic&report=effectiveSettings&routeType=eco&traffic=true&avoid=unpavedRoads&travelMode=bus" +
                "&vehicleMaxSpeed=120&vehicleEngineType=combustion&key=" + apiKey, TomTomMain.class, cityFrom, cityTo);

        return RouteDto.builder()
                .travelTimeInSeconds(routes.getRoutes().get(0).getSummary().getTravelTimeInSeconds())
                .lengthInMeters(routes.getRoutes().get(0).getSummary().getLengthInMeters())
                .trafiicDelayInSeconds(routes.getRoutes().get(0).getSummary().getTrafiicDelayInSeconds())
                .cityFrom(cityFrom)
                .cityTo(cityTo)
                .build();
    }


}
