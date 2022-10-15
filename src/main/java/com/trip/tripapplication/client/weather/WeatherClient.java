package com.trip.tripapplication.client.weather;

import com.trip.tripapplication.client.weather.dto.CurrentWeatherDto;
import com.trip.tripapplication.domain.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class WeatherClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.endpoint}")
    private String endpoint;

    public WeatherDto getCurrentWeather(double lat, double lon) {
        CurrentWeatherDto current_weather = restTemplate.getForObject(
                endpoint + "latitude={lat}&longitude={lon}&current_weather=true", CurrentWeatherDto.class, lat, lon
        );
        return WeatherDto.builder()
                .temperature(current_weather.getCurrent_weather().getTemperature())
                .windspeed(current_weather.getCurrent_weather().getWindspeed())
                .weathercode(current_weather.getCurrent_weather().getWeathercode())
                .build();
    }


}
