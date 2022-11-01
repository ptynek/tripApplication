package com.trip.tripapplication.client.weather.dto;

import com.trip.tripapplication.domain.dto.WeatherCodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientWeatherDto {

    private long id;
    private double temperature;
    private double windspeed;
    private int weatherCode;
}
