package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.tomtom.TomTomClient;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.RouteDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.service.CitiesDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final TomTomClient tomTomClient;
    private final CitiesDbService citiesDbService;

    @GetMapping
    public RouteDto getRoute(@RequestParam(name = "idCityFrom") long idCityFrom,
                             @RequestParam(name= "idCityTo") long idCityTo) throws CitiesException {
        Cities cityFrom = citiesDbService.getCityById(idCityFrom);
        Cities cityTo = citiesDbService.getCityById(idCityTo);
        return tomTomClient.getRoute(cityFrom, cityTo);
    }
}
