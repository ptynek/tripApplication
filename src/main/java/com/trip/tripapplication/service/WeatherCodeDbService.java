package com.trip.tripapplication.service;

import com.trip.tripapplication.domain.WeatherCode;
import com.trip.tripapplication.exceptions.WeatherCodeException;
import com.trip.tripapplication.repository.WeatherCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherCodeDbService {

    private final WeatherCodeRepository weatherCodeRepository;

    public List<WeatherCode> getAllWeatherCodes(){
        return weatherCodeRepository.findAll();
    }

    public WeatherCode getWeatherCodeByCode(final int id) {
        return weatherCodeRepository.findByWeatherCode(id);
    }

    public WeatherCode saveWeatherCode(final WeatherCode weatherCode){
        return weatherCodeRepository.save(weatherCode);
    }


}
