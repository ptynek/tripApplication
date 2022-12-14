package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.tomtom.TomTomClient;
import com.trip.tripapplication.client.weather.WeatherClient;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.RouteDto;
import com.trip.tripapplication.domain.dto.WeatherDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.exceptions.CityNotActive;
import com.trip.tripapplication.exceptions.PassengerNotActive;
import com.trip.tripapplication.exceptions.PassengerNotLoggedIn;
import com.trip.tripapplication.mapper.CitiesMapper;
import com.trip.tripapplication.mapper.RouteMapper;
import com.trip.tripapplication.mapper.WeatherMapper;
import com.trip.tripapplication.repository.RouteRepository;
import com.trip.tripapplication.service.CitiesDbService;
import com.trip.tripapplication.service.PriceSettingsDbService;
import com.trip.tripapplication.service.RouteDbService;
import com.trip.tripapplication.service.WeatherDbService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/route")
@RequiredArgsConstructor
@Slf4j
public class RouteController {

    private final TomTomClient tomTomClient;
    private final RouteDbService routeDbService;
    private final CitiesDbService citiesDbService;
    private final RouteMapper routeMapper;
    private final CitiesMapper citiesMapper;
    private final WeatherDbService weatherDbService;
    private final PriceSettingsDbService priceSettingsDbService;


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

    @PostMapping(params = {"idCityFrom", "idCityTo", "dateTime"})
    public ResponseEntity<RouteDto> addRoute(@RequestParam(name = "idCityFrom") long idCityFrom,
                                             @RequestParam(name = "idCityTo") long idCityTo,
                                             @RequestParam(name = "dateTime", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) throws CitiesException, PassengerNotLoggedIn, PassengerNotActive, CityNotActive {

        Cities cityFrom = citiesDbService.getCityById(idCityFrom);
        Cities cityTo = citiesDbService.getCityById(idCityTo);

        CitiesDto cityFromDto = citiesMapper.mapToCitiesDto(cityFrom);
        CitiesDto cityToDto = citiesMapper.mapToCitiesDto(cityTo);

        if (citiesDbService.checkIfCityIsActive(cityFrom, cityTo)){
            if (routeDbService.checkIfIsLoggedIn()) {
                Route route = routeMapper.mapToRoute(tomTomClient.getRoute(cityFromDto, cityToDto));
                route.setWeather(weatherDbService.getWeatherInDestinationCity(cityToDto));
                route.setPassengers(routeDbService.getPassenger());
                route.setDateOfTrip(dateTime);
                route.setPrice(priceSettingsDbService.getPricePerKm().multiply(new BigDecimal(route.getLengthInMeters()/1000)).setScale(2, RoundingMode.HALF_UP));
                routeDbService.saveRoute(route);
                return ResponseEntity.ok().build();
            } else {
                throw new PassengerNotLoggedIn();
            }
        } else {
            throw new CityNotActive();
        }
    }
}
