package com.trip.tripapplication.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*@NamedQuery(
        name = "Cities.findByCity",
        query = "FROM Cities WHERE city = :CITY"
)*/
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

}
