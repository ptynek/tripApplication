package com.trip.tripapplication.client.weather.dto;

import com.trip.tripapplication.domain.dto.WeatherDto;
import lombok.Getter;

@Getter
public class CurrentWeatherDto {

    private WeatherDto current_weather;
}