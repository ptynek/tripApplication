package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CreatePassengerDto;
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
                passengers.getLastName()
        );
    }

    public CreatePassengerDto mapToCreatePassengerDto(final Passengers passengers){
        return new CreatePassengerDto(
                passengers.getId(),
                passengers.getFirstName(),
                passengers.getLastName(),
                passengers.getPhoneNumber(),
                passengers.getMail()
        );
    }

    public Passengers mapToPassengers(final CreatePassengerDto createPassengerDto){
        return new Passengers(
                createPassengerDto.getId(),
                createPassengerDto.getFirstName(),
                createPassengerDto.getLastName(),
                createPassengerDto.getPhoneNumber(),
                createPassengerDto.getMail()
        );
    }

    public List<PassengersDto> mapToPassengersDtoList(final List<Passengers> passengersList){
        return passengersList.stream()
                .map(this::mapToPassengersDto)
                .collect(Collectors.toList());
    }
}
