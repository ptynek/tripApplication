package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.weather.WeatherClient;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.RouteDto;
import com.trip.tripapplication.domain.dto.WeatherDto;
import com.trip.tripapplication.exceptions.CitiesException;
import com.trip.tripapplication.repository.CitiesRepository;
import com.trip.tripapplication.service.CitiesDbService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherClient weatherClient;
    private final CitiesDbService citiesDbService;

    @GetMapping(params = {"lat", "lon"})
    public ResponseEntity<WeatherDto> getWeatherByCoordinates(@RequestParam(name = "lat") double lat,
                                                 @RequestParam(name = "lon") double lon){
        return ResponseEntity.ok(weatherClient.getCurrentWeather(lat, lon));
    }

    @GetMapping(value = "{idCity}")
    public ResponseEntity<WeatherDto> getWeatherByCityId(@PathVariable long idCity) throws CitiesException{
            Cities cities = citiesDbService.getCityById(idCity);
            WeatherDto weather = weatherClient.getCurrentWeather(cities.getLatitude(), cities.getLongitude());
            log.info(
                    "Weather for " + cities.getCity() + " is " + weather.getWeatherCodeDto().getDescription()
            );
        return ResponseEntity.ok(weather);
    }
}
