package com.trip.tripapplication.service;

import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.exceptions.PassengersException;
import com.trip.tripapplication.repository.PassengersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PassengerDbService {
    private final PassengersRepository repository;

    public List<Passengers> getAllPassengers(){
        return repository.findAll();
    }

    public Passengers getPassenger(final Long id) throws PassengersException {
        return repository.findById(id).orElseThrow(PassengersException::new);
    }

    public Passengers savePassenger(final Passengers passengers){
        return repository.save(passengers);
    }

    public void deletePassenger(final Long id){
        repository.deleteById(id);
    }
}
