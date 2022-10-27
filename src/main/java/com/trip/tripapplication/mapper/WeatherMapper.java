package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Weather;
import com.trip.tripapplication.domain.dto.WeatherDto;
import com.trip.tripapplication.repository.WeatherCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherMapper {

    private final WeatherCodeRepository weatherCodeRepository;

    public Weather mapToWeather(final WeatherDto weatherDto){
        return new Weather(
                weatherDto.getId(),
                weatherDto.getTemperature(),
                weatherDto.getWindspeed(),
                weatherDto.getWeathercode()
        );
    }

    public WeatherDto mapToWeatherDto(final Weather weather){
        return new WeatherDto(
                weather.getId(),
                weather.getTemperature(),
                weather.getWindspeed(),
                weather.getWeathercode()
        );
    }
}
