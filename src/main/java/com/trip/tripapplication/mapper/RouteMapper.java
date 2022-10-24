package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.domain.dto.RouteDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.service.CitiesDbService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteMapper {
    private CitiesMapper citiesMapper;
    private WeatherCodeMapper weatherCodeMapper;

    public Route mapToRoute(final RouteDto routeDto) {
        return new Route(
                routeDto.getId(),
                routeDto.getLengthInMeters(),
                routeDto.getTravelTimeInSeconds(),
                routeDto.getTrafiicDelayInSeconds(),
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
                citiesMapper.mapToCitiesDto(route.getCityTo())
        );
    }

    public List<RouteDto> mapToRouteDtoList(final List<Route> routes) {
        return routes.stream()
                .map(this::mapToRouteDto)
                .collect(Collectors.toList());
    }

}
