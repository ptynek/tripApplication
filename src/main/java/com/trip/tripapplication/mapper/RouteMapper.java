package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.domain.dto.RouteDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RouteMapper {
    private CitiesMapper citiesMapper;
    private PassengersMapper passengersMapper;
    private WeatherMapper weatherMapper;

    public Route mapToRoute(final RouteDto routeDto){
        return new Route(
                routeDto.getId(),
                routeDto.getLengthInMeters(),
                routeDto.getTravelTimeInSeconds(),
                routeDto.getTraficDelayInSeconds(),
                citiesMapper.mapToCities(routeDto.getCityFrom()),
                citiesMapper.mapToCities(routeDto.getCityTo())
        );
    }
    public RouteDto mapToRouteDto(final Route route){
        return new RouteDto(
                route.getId(),
                route.getLengthInMeters(),
                route.getTravelTimeInSeconds(),
                route.getTrafiicDelayInSeconds(),
                citiesMapper.mapToCitiesDto(route.getCityFrom()),
                citiesMapper.mapToCitiesDto(route.getCityTo()),
                passengersMapper.mapToPassengersDto(route.getPassengers()),
                weatherMapper.mapToWeatherDto(route.getWeather()),
                route.getDateOfTrip(),
                route.getPrice()
        );
    }

    public List<RouteDto> mapToRouteDtoList(final List<Route> routes) {
        return routes.stream()
                .map(this::mapToRouteDto)
                .collect(Collectors.toList());
    }

}
