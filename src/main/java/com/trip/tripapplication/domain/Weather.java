package com.trip.tripapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)

    private long id;
    @OneToOne
    @JoinColumn(name = "city_id")
    private Cities city;

    @Column(name = "TEMPERATURE")
    private double temperature;

    @Column(name = "WIND_SPEED")
    private double windspeed;

    @Column(name = "WEATHER_CODE")
    private int weathercode;

}
