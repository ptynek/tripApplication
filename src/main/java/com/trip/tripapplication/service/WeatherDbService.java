package com.trip.tripapplication.service;

import com.trip.tripapplication.client.weather.WeatherClient;
import com.trip.tripapplication.domain.Weather;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.WeatherDto;
import com.trip.tripapplication.mapper.WeatherMapper;
import com.trip.tripapplication.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherDbService {

    private final WeatherRepository weatherRepository;
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;

    public Weather saveWeather(final Weather weather){
        return weatherRepository.save(weather);
    }

    public Weather getWeatherInDestinationCity(final CitiesDto citiesDto){
        WeatherDto weatherInDestinationCityDto = weatherClient.getCurrentWeather(citiesDto.getLatitude(), citiesDto.getLongitude());
        Weather weatherInDestinationCity = weatherMapper.mapToWeather(weatherInDestinationCityDto);
        saveWeather(weatherInDestinationCity);
        return weatherInDestinationCity;
    }
}
