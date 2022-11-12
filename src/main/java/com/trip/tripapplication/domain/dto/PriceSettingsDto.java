package com.trip.tripapplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceSettingsDto {

    private long id;
    private LocalDateTime dateOfModification;
    private BigDecimal pricePerKilometer;

    public PriceSettingsDto(long id, BigDecimal pricePerKilometer) {
        this.id = id;
        this.dateOfModification = LocalDateTime.now();
        this.pricePerKilometer = pricePerKilometer;
    }
}
