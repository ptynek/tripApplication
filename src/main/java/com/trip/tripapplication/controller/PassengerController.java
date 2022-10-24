package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CreatePassengerDto;
import com.trip.tripapplication.domain.dto.PassengersDto;
import com.trip.tripapplication.exceptions.PassengersException;
import com.trip.tripapplication.mapper.PassengersMapper;
import com.trip.tripapplication.service.PassengerDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/passengers")
@RequiredArgsConstructor
@Slf4j
public class PassengerController {

    private final PassengerDbService service;

    private final PassengersMapper mapper;

    @GetMapping
    public ResponseEntity<List<PassengersDto>> getPassengers(){
        List<Passengers> passengers = service.getAllPassengers();
        return ResponseEntity.ok(mapper.mapToPassengersDtoList(passengers));
    }

    @GetMapping(value = "{passengerId}")
    public ResponseEntity<PassengersDto> getPassenger(@PathVariable long passengerId) throws PassengersException{
        return ResponseEntity.ok(mapper.mapToPassengersDto(service.getPassenger(passengerId)));
    }

    @PutMapping
    public ResponseEntity<CreatePassengerDto> updatePassenger(@RequestBody CreatePassengerDto createPassengerDto){
        Passengers passenger = mapper.mapToPassengers(createPassengerDto);
        Passengers savedPassenger = service.savePassenger(passenger);
        return ResponseEntity.ok(mapper.mapToCreatePassengerDto(savedPassenger));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPassenger(@RequestBody CreatePassengerDto createPassengerDto){
        Passengers passenger = mapper.mapToPassengers(createPassengerDto);
        passenger.setActive(true);
        log.info(String.valueOf("Creating passenger: " + passenger));
        service.savePassenger(passenger);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable long passengerId) throws PassengersException {
        log.info("Deleting passenger:" + service.getPassenger(passengerId));
        service.deletePassenger(passengerId);
        return ResponseEntity.ok().build();
    }
}
