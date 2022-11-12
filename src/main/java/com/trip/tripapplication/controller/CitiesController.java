package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.mapper.CitiesMapper;
import com.trip.tripapplication.service.CitiesDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cities")
@RequiredArgsConstructor
@Slf4j
public class CitiesController {

    private final CitiesDbService service;
    private final CitiesMapper mapper;

    @GetMapping
    public ResponseEntity<List<CitiesDto>> getCities(){
        log.info("Get all cities");
        List<Cities> cities = service.getAllCities();
        return ResponseEntity.ok(mapper.mapToCitiesDtoList(cities));
    }

    @GetMapping(value = "{cityId}")
    public ResponseEntity<CitiesDto> getCity(@PathVariable long cityId) throws CitiesException{
        log.info("Get city with id: " + cityId);
        return ResponseEntity.ok(mapper.mapToCitiesDto(service.getCityById(cityId)));
    }

    @GetMapping(value = "/name", params = {"city"})
    public ResponseEntity<CitiesDto> getCityByName(@RequestParam("city") String cityName){
        log.info("Get city: " + cityName);
        return ResponseEntity.ok(mapper.mapToCitiesDto(service.getCiyByName(cityName)));
    }

    @PutMapping
    public ResponseEntity<CitiesDto> updateCity(@RequestBody CitiesDto citiesDto){
        log.info("Update city: " + citiesDto.getCity());

        Cities cities = mapper.mapToCities(citiesDto);
        Cities savedCity = service.saveCity(cities);
        return ResponseEntity.ok(mapper.mapToCitiesDto(savedCity));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCity(@RequestBody CitiesDto citiesDto){
        log.info("Add city: " + citiesDto.getCity());
        Cities cities = mapper.mapToCities(citiesDto);
        cities.setActive(true);
        service.saveCity(cities);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable long cityId) throws CitiesException{
        log.info("Deactivate city id: " + cityId);
        service.deleteCity(cityId);
        return ResponseEntity.ok().build();
    }
}
