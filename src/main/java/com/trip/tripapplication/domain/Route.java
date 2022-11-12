package com.trip.tripapplication.domain;

import com.trip.tripapplication.mapper.PassengersMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @OneToOne
    @JoinColumn(name = "passengers_id")
    private Passengers passengers;

    @OneToOne
    @JoinColumn(name = "weather_id")
    private Weather weather;

    @Column(name = "DATE")
    private LocalDateTime dateOfTrip;


    public Route(long id, long lengthInMeters, long travelTimeInSeconds, long trafiicDelayInSeconds, Cities cityFrom, Cities cityTo) {
        this.id = id;
        this.lengthInMeters = lengthInMeters;
        this.travelTimeInSeconds = travelTimeInSeconds;
        this.trafiicDelayInSeconds = trafiicDelayInSeconds;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }
}
