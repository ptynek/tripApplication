package com.trip.tripapplication.exceptions;

import com.trip.tripapplication.domain.Cities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CitiesException.class)
    public ResponseEntity<Object> cityNotFoundException(CitiesException citiesException){
        return new ResponseEntity<>("City with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PassengersException.class)
    public ResponseEntity<Object> passengerNotFoundException(PassengersException citiesException){
        return new ResponseEntity<>("Passenger with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeatherCodeException.class)
    public ResponseEntity<Object> passengerNotFoundException(WeatherCodeException weatherCodeException){
        return new ResponseEntity<>("Weather code with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PassengerNotLoggedIn.class)
    public ResponseEntity<Object> passengerNotLoggedIn(PassengerNotLoggedIn passengerNotLoggedIn){
        return new ResponseEntity<>("Please log in passenger!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PassengerNotActive.class)
    public ResponseEntity<Object> passengerNotActive(PassengerNotActive passengerNotActive){
        return new ResponseEntity<>("Passenger is not active!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityNotActive.class)
    public ResponseEntity<Object> cityNotActive(CityNotActive cityNotActive){
        return new ResponseEntity<>("City is not active anymore", HttpStatus.BAD_REQUEST);
    }
}
