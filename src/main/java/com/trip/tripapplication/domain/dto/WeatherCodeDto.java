package com.trip.tripapplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherCodeDto {

    private long id;
    private int weatherCode;
    private String description;
}
