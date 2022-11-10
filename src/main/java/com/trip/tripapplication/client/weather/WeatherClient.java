package com.trip.tripapplication.client.weather;

import com.trip.tripapplication.client.weather.dto.CurrentWeatherDto;
import com.trip.tripapplication.domain.WeatherCode;
import com.trip.tripapplication.domain.dto.WeatherDto;
import com.trip.tripapplication.mapper.WeatherCodeMapper;
import com.trip.tripapplication.repository.WeatherCodeRepository;
import com.trip.tripapplication.service.WeatherCodeDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
@Slf4j
public class WeatherClient {

    private final RestTemplate restTemplate;

    private final WeatherCodeRepository weatherCodeRepository;
    private final WeatherCodeMapper weatherCodeMapper;


    @Value("${weather.api.endpoint}")
    private String endpoint;

    public WeatherDto getCurrentWeather(double lat, double lon) {
        CurrentWeatherDto current_weather = restTemplate.getForObject(
                endpoint + "latitude={lat}&longitude={lon}&current_weather=true", CurrentWeatherDto.class, lat, lon
        );

        WeatherCode weatherCode = weatherCodeRepository.findByWeatherCode(current_weather.getCurrent_weather().getWeathercode());

        return WeatherDto.builder()
                .temperature(current_weather.getCurrent_weather().getTemperature())
                .windspeed(current_weather.getCurrent_weather().getWindspeed())
                .weatherCodeDto(weatherCodeMapper.mapToWeatherCodeDto(weatherCode))
                .build();
    }


}
