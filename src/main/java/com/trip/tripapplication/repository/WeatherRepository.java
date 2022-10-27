package com.trip.tripapplication.repository;

import com.trip.tripapplication.domain.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Override
    Weather save(final Weather weather);
}
