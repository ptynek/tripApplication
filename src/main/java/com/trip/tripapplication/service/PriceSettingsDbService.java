package com.trip.tripapplication.service;


import com.trip.tripapplication.domain.PriceSettings;
import com.trip.tripapplication.repository.PriceSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceSettingsDbService {

    private final PriceSettingsRepository repository;

    public List<PriceSettings> getAllPriceSettings(){
        return repository.findAll();
    }

    public PriceSettings getPriceSettings(final long id) throws Exception{
        return repository.findById(id).orElseThrow(Exception::new);
    }

    public PriceSettings savePriceSettings(final PriceSettings priceSettings){
        return repository.save(priceSettings);
    }
}
