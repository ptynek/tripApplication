package com.trip.tripapplication.service;


import com.trip.tripapplication.domain.PriceSettings;
import com.trip.tripapplication.domain.dto.PriceSettingsDto;
import com.trip.tripapplication.repository.PriceSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceSettingsDbService {

    private final PriceSettingsRepository repository;

    public List<PriceSettings> getAllPriceSettings(){
        return repository.findAll();
    }

    public PriceSettings savePriceSettings(final PriceSettings priceSettings){
        return repository.save(priceSettings);
    }

    public BigDecimal getPricePerKm() {

        List<PriceSettings> priceList = getAllPriceSettings();

        LocalDateTime maxDate = priceList.stream()
                .map(PriceSettings::getDateOfModification)
                .max(LocalDateTime::compareTo)
                .get();

        return priceList.stream()
                .filter(priceSettingsDto -> maxDate.equals(priceSettingsDto.getDateOfModification()))
                .findFirst()
                .get().getPricePerKilometer();
    }
}
