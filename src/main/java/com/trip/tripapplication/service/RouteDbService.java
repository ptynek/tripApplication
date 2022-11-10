package com.trip.tripapplication.service;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.domain.dto.PassengersDto;
import com.trip.tripapplication.exceptions.PassengerNotActive;
import com.trip.tripapplication.exceptions.PassengerNotLoggedIn;
import com.trip.tripapplication.exceptions.PassengersException;
import com.trip.tripapplication.repository.PassengersRepository;
import com.trip.tripapplication.repository.RouteRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class RouteDbService {

    private final PassengersRepository passengersRepository;

    private final RouteRepository repository;

    public Passengers passenger;


    public List<Route> getAllRoutes(){
        return repository.findAll();
    }

    public Route saveRoute(final Route route){
        return repository.save(route);
    }

    public boolean checkIfIsLoggedIn() throws PassengerNotLoggedIn, PassengerNotActive {
        List<Passengers> passengersList = passengersRepository.findAll();
        List<Passengers> passengerLoggedIn = new ArrayList<>();
        try{
            passengersList.stream()
                    .filter(Passengers::isLoggedIn)
                    .forEach(passengerLoggedIn::add);
        } catch (NullPointerException nullPointerException){
            log.info(String.valueOf(nullPointerException));
        } finally {
            if (passengerLoggedIn.size() != 0 && passengerLoggedIn.get(0).isLoggedIn()) {
                if (passengerLoggedIn.get(0).isActive()) {
                    passenger = passengerLoggedIn.get(0);
                    return true;
                } else {
                    throw new PassengerNotActive();
                }
            } else {
                throw new PassengerNotLoggedIn();
            }
        }
    }

}
