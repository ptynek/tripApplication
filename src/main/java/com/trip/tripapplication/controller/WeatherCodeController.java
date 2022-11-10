package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.WeatherCode;
import com.trip.tripapplication.domain.dto.WeatherCodeDto;
import com.trip.tripapplication.exceptions.WeatherCodeException;
import com.trip.tripapplication.mapper.WeatherCodeMapper;
import com.trip.tripapplication.repository.WeatherCodeRepository;
import com.trip.tripapplication.service.WeatherCodeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/weather_codes")
@RequiredArgsConstructor
public class WeatherCodeController {

    private final WeatherCodeDbService service;
    private final WeatherCodeMapper mapper;

    @GetMapping
    public ResponseEntity<List<WeatherCodeDto>> getAllWeatherCodes(){
        List<WeatherCode> weatherCodes = service.getAllWeatherCodes();
        return ResponseEntity.ok(mapper.mapToWeatherCodeDtoList(weatherCodes));
    }

    @GetMapping(params = {"weatherCode"})
    public ResponseEntity<WeatherCodeDto> getWeatherCode(@RequestParam(name = "weatherCode") int weatherCode){
        return ResponseEntity.ok(mapper.mapToWeatherCodeDto(service.getWeatherCodeByCode(weatherCode)));
    }

    @PostMapping
    public ResponseEntity<Void> addWeatherCode(@RequestBody WeatherCodeDto weatherCodeDto){
        WeatherCode weatherCode = mapper.mapToWeatherCode(weatherCodeDto);
        service.saveWeatherCode(weatherCode);
        return ResponseEntity.ok().build();
    }

}
