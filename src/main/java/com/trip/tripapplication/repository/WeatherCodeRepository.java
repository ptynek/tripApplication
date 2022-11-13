package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.WeatherCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherCodeRepository extends CrudRepository <WeatherCode, Long> {


    WeatherCode findByWeatherCode(final int weatherCode);

    @Override
    WeatherCode save(final WeatherCode weatherCode);

    @Override
    List<WeatherCode> findAll();

}
