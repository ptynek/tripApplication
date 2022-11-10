package com.trip.tripapplication.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CITIES")
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "COUNTRY")
    private String country;

    @NotNull
    @Column(name = "LATITUDE")
    private double latitude;

    @NotNull
    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "ACTIVE")
    private boolean active;

    public Cities(long id, String city, String country, double latitude, double longitude) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
