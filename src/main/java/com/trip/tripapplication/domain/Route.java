package com.trip.tripapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ROUTES")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "LENGTH")
    private long lengthInMeters;

    @Column(name = "TRAVEL_TIME")
    private long travelTimeInSeconds;

    @Column(name = "TRAFFIC_DELAY")
    private long trafiicDelayInSeconds;

    @OneToOne
    @JoinColumn(name = "city_from_id")
    private Cities cityFrom;
    @OneToOne
    @JoinColumn(name = "city_to_id")
    private Cities cityTo;

}