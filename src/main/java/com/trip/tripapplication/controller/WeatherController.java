package com.trip.tripapplication.controller;

import com.trip.tripapplication.client.weather.WeatherClient;
import com.trip.tripapplication.domain.dto.WeatherDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherClient weatherClient;

    @GetMapping
    public WeatherDto getWeather(){
        return weatherClient.getCurrentWeather(51.11326, 17.02928);
    }
}
