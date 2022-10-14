package com.trip.tripapplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitiesDto {

    private long id;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    public CitiesDto(String city, String country, double latitude, double longitude) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
