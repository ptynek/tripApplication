package com.trip.tripapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "WEATHER_CODE")
public class WeatherCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE", unique = true)
    private int weatherCode;

    @Column(name = "DESCRIPTION")
    private String description;
}
