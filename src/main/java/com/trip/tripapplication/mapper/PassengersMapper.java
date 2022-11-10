package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.PassengersDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengersMapper {

    public PassengersDto mapToPassengersDto(final Passengers passengers) {
        return new PassengersDto(
                passengers.getId(),
                passengers.getFirstName(),
                passengers.getLastName(),
                passengers.getPhoneNumber(),
                passengers.getMail(),
                passengers.isActive(),
                passengers.isLoggedIn()
        );
    }

    public Passengers mapToPassengers(final PassengersDto passengersDto){
        return new Passengers(
                passengersDto.getId(),
                passengersDto.getFirstName(),
                passengersDto.getLastName(),
                passengersDto.getPhoneNumber(),
                passengersDto.getMail(),
                passengersDto.isActive(),
                passengersDto.isLoggedIn()
        );
    }

    public List<PassengersDto> mapToPassengersDtoList(final List<Passengers> passengersList){
        return passengersList.stream()
                .map(this::mapToPassengersDto)
                .collect(Collectors.toList());
    }
}
