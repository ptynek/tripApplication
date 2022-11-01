package com.trip.tripapplication;

import com.trip.tripapplication.domain.Weather;
import com.trip.tripapplication.domain.WeatherCode;
import com.trip.tripapplication.repository.WeatherCodeRepository;
import com.trip.tripapplication.service.WeatherDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TripapplicationApplication {

    public static void main(String[] args) {

        SpringApplication.run(TripapplicationApplication.class, args);

    }

}
