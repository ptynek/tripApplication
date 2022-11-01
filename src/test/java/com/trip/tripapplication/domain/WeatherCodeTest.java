package com.trip.tripapplication.domain;

import com.trip.tripapplication.repository.WeatherCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherCodeTest {
/*
    @Autowired
    private WeatherCodeRepository weatherCodeRepository;

    @Test
    void testAddWeatherCodes() {

        WeatherCode weatherCode0 = new WeatherCode(0, "Clear sky");
        WeatherCode weatherCode1 = new WeatherCode(1, "Mainly clear");
        WeatherCode weatherCode2 = new WeatherCode(2, "Partly cloudy");
        WeatherCode weatherCode3 = new WeatherCode(3, "Overcast");
        WeatherCode weatherCode45 = new WeatherCode(45, "Fog");
        WeatherCode weatherCode48 = new WeatherCode(48, "Depositing rime fog");
        WeatherCode weatherCode51 = new WeatherCode(51, "Light drizzle");
        WeatherCode weatherCode53 = new WeatherCode(53, "Moderate drizzle");
        WeatherCode weatherCode55 = new WeatherCode(55, "Dense intensity drizzle");
        WeatherCode weatherCode56 = new WeatherCode(56, "Light freezing drizzle");
        WeatherCode weatherCode57 = new WeatherCode(57, "Dense intensity freezing drizzle");
        WeatherCode weatherCode61 = new WeatherCode(61, "Slight rain");
        WeatherCode weatherCode63 = new WeatherCode(63, "Moderate rain");
        WeatherCode weatherCode65 = new WeatherCode(65, "Heavy intensity rain");
        WeatherCode weatherCode66 = new WeatherCode(66, "Light freezing rain");
        WeatherCode weatherCode67 = new WeatherCode(67,"Heavy intensity freezing rain");
        WeatherCode weatherCode71 = new WeatherCode(71,"Slight snow fall");
        WeatherCode weatherCode73 = new WeatherCode(73, "Moderate snow fall");
        WeatherCode weatherCode75 = new WeatherCode(75, "have intensity snow fall");
        WeatherCode weatherCode77 = new WeatherCode(77, "Snow grain");
        WeatherCode weatherCode80 = new WeatherCode(80, "Slight rain shower");
        WeatherCode weatherCode81 = new WeatherCode(81, "Moderate rain shower");
        WeatherCode weatherCode82 = new WeatherCode(82, "Violent rain shower");
        WeatherCode weatherCode85 = new WeatherCode(85, "Slight snow shower");
        WeatherCode weatherCode86 = new WeatherCode(86, "Heavy snow shower");
        WeatherCode weatherCode95 = new WeatherCode(95, "Thunderstorm");
        WeatherCode weatherCode96 = new WeatherCode(96, "Slight hail thunderstorm");
        WeatherCode weatherCode99 = new WeatherCode(99, "Heavy hail thunderstorm");

        weatherCodeRepository.save(weatherCode0);
        weatherCodeRepository.save(weatherCode1);
        weatherCodeRepository.save(weatherCode2);
        weatherCodeRepository.save(weatherCode3);
        weatherCodeRepository.save(weatherCode45);
        weatherCodeRepository.save(weatherCode48);
        weatherCodeRepository.save(weatherCode51);
        weatherCodeRepository.save(weatherCode53);
        weatherCodeRepository.save(weatherCode55);
        weatherCodeRepository.save(weatherCode56);
        weatherCodeRepository.save(weatherCode57);
        weatherCodeRepository.save(weatherCode61);
        weatherCodeRepository.save(weatherCode63);
        weatherCodeRepository.save(weatherCode65);
        weatherCodeRepository.save(weatherCode66);
        weatherCodeRepository.save(weatherCode67);
        weatherCodeRepository.save(weatherCode71);
        weatherCodeRepository.save(weatherCode73);
        weatherCodeRepository.save(weatherCode75);
        weatherCodeRepository.save(weatherCode77);
        weatherCodeRepository.save(weatherCode80);
        weatherCodeRepository.save(weatherCode81);
        weatherCodeRepository.save(weatherCode82);
        weatherCodeRepository.save(weatherCode85);
        weatherCodeRepository.save(weatherCode86);
        weatherCodeRepository.save(weatherCode95);
        weatherCodeRepository.save(weatherCode96);
        weatherCodeRepository.save(weatherCode99);

        assertEquals(28, weatherCodeRepository.findAll().size());

    }*/

}