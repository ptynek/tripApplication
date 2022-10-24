package com.trip.tripapplication.service;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CitiesDbService {
    private final CitiesRepository repository;

    public List<Cities> getAllCities(){
        return repository.findAll();
    }

    public Cities getCityById(final Long id) throws CitiesException {
        return repository.findById(id).orElseThrow(CitiesException::new);
    }

    public Cities saveCity( final Cities cities){
        return repository.save(cities);
    }

    public List<Cities> getCiyByName(final String name){
        return repository.findByCity(name);
    }

    public void deleteCity(final Long id) throws  CitiesException{
        Cities city = repository.findById(id).orElseThrow(CitiesException::new);
        city.setActive(false);
        repository.save(city);
    }
}
