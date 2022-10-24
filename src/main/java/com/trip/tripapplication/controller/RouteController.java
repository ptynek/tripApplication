package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.tomtom.TomTomClient;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.RouteDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.exceptions.PassengerNotActive;
import com.trip.tripapplication.exceptions.PassengerNotLoggedIn;
import com.trip.tripapplication.mapper.CitiesMapper;
import com.trip.tripapplication.mapper.RouteMapper;
import com.trip.tripapplication.repository.RouteRepository;
import com.trip.tripapplication.service.CitiesDbService;
import com.trip.tripapplication.service.RouteDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final TomTomClient tomTomClient;
    private final RouteDbService routeDbService;
    private final CitiesDbService citiesDbService;
    private final RouteMapper routeMapper;
    private final CitiesMapper citiesMapper;


    @GetMapping(params = {"idCityFrom", "idCityTo"})
    public ResponseEntity<RouteDto> getRoute(@RequestParam(name = "idCityFrom") long idCityFrom,
                                             @RequestParam(name= "idCityTo") long idCityTo) throws CitiesException{

        CitiesDto cityFrom = citiesMapper.mapToCitiesDto(citiesDbService.getCityById(idCityFrom));
        CitiesDto cityTo = citiesMapper.mapToCitiesDto(citiesDbService.getCityById(idCityTo));

        return ResponseEntity.ok(tomTomClient.getRoute(cityFrom, cityTo));
    }

    @GetMapping
    public List<RouteDto> getAllRoutes() {
        List<Route> routes = routeDbService.getAllRoutes();
        return routeMapper.mapToRouteDtoList(routes);
    }

    @PostMapping(params = {"idCityFrom", "idCityTo"})
    public ResponseEntity<RouteDto> addRoute(@RequestParam(name = "idCityFrom") long idCityFrom,
                                             @RequestParam(name = "idCityTo") long idCityTo) throws CitiesException, PassengerNotLoggedIn, PassengerNotActive {

        CitiesDto cityFrom = citiesMapper.mapToCitiesDto(citiesDbService.getCityById(idCityFrom));
        CitiesDto cityTo = citiesMapper.mapToCitiesDto(citiesDbService.getCityById(idCityTo));

        if (routeDbService.checkIfIsLoggedIn()) {
                Route route = routeMapper.mapToRoute(tomTomClient.getRoute(cityFrom, cityTo));
                route.setPassengers(routeDbService.passenger);
                routeDbService.saveRoute(route);
            return ResponseEntity.ok().build();
        } else {
            throw new PassengerNotLoggedIn();
        }
    }
}
