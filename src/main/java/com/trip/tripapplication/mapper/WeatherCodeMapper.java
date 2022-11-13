package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.WeatherCode;
import com.trip.tripapplication.domain.dto.WeatherCodeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WeatherCodeMapper {

    public WeatherCode mapToWeatherCode(final WeatherCodeDto weatherCodeDto){
        return new WeatherCode(
                weatherCodeDto.getId(),
                weatherCodeDto.getWeatherCode(),
                weatherCodeDto.getDescription()
        );
    }

    public WeatherCodeDto mapToWeatherCodeDto(final WeatherCode weatherCode){
        return new WeatherCodeDto(
                weatherCode.getId(),
                weatherCode.getWeatherCode(),
                weatherCode.getDescription()
        );
    }

    public List<WeatherCodeDto> mapToWeatherCodeDtoList(final List<WeatherCode> weatherCodeList){
        return weatherCodeList.stream()
                .map(this::mapToWeatherCodeDto)
                .collect(Collectors.toList());
    }
}
