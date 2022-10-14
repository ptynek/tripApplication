package com.trip.tripapplication.exceptions;

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
}
