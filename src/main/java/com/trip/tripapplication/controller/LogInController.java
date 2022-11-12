package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.exceptions.PassengersException;
import com.trip.tripapplication.service.PassengerDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/passengerlogin")
@RequiredArgsConstructor
@Slf4j
public class LogInController {

    private final PassengerDbService passengerDbService;

    @PutMapping(value = "{passengerId}")
    public ResponseEntity<Void> logInPassenger(@PathVariable long passengerId) throws PassengersException{
        log.info("Log in passenger: " + passengerId);
        Passengers passenger = passengerDbService.getPassenger(passengerId);
        passengerDbService.logInAsPassenger(passenger.getId());
        return ResponseEntity.ok().build();
    }

}