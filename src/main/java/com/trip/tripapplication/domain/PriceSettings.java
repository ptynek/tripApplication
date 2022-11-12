package com.trip.tripapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PRICE_SETTINGS")
public class PriceSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "DATETIME_MODIFICATION")
    private LocalDateTime dateOfModification;

    @Column(name = "PRICE_PER_KM")
    private BigDecimal pricePerKilometer;

    public PriceSettings(BigDecimal pricePerKilometer) {
        this.dateOfModification = LocalDateTime.now();
        this.pricePerKilometer = pricePerKilometer;
    }
}
