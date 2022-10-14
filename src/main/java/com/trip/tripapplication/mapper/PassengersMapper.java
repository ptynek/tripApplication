package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CreatePassengerDto;
import com.trip.tripapplication.domain.dto.PassengersDto;
import org.springframework.stereotype.Service;

@Service
public class PassengersMapper {

    public PassengersDto mapToPassengersDto(final Passengers passengers) {
        return new PassengersDto(
                passengers.getId(),
                passengers.getFirstName(),
                passengers.getLastname()
        );
    }

    public Passengers mapToPassengers(final CreatePassengerDto createPassengerDto){
        return new Passengers(
                createPassengerDto.getId(),
                createPassengerDto.getFirstName(),
                createPassengerDto.getLastname(),
                createPassengerDto.getPhoneNumber(),
                createPassengerDto.getMail()
        );
    }
}
