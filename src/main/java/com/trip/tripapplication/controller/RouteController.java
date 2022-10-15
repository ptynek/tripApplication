package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.tomtom.TomTomClient;
import com.trip.tripapplication.domain.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final TomTomClient tomTomClient;

    @GetMapping
    public RouteDto getRoute(){
        return tomTomClient.getRoute(51.11326, 17.02928, 52.17394, 20.90944);
    }
}
