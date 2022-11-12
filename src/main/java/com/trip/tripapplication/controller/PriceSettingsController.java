package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.PriceSettings;
import com.trip.tripapplication.domain.dto.PriceSettingsDto;
import com.trip.tripapplication.mapper.PriceSettingsMapper;
import com.trip.tripapplication.service.PriceSettingsDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/priceSettings")
@RequiredArgsConstructor
@Slf4j
public class PriceSettingsController {

    private final PriceSettingsDbService service;
    private final PriceSettingsMapper mapper;

    @GetMapping
    public ResponseEntity<List<PriceSettingsDto>> getAllPriceSettings(){
        log.info("Get all prices");
        List<PriceSettings> priceSettingsList = service.getAllPriceSettings();
        return ResponseEntity.ok(mapper.mapToPriceSettingsDtoList(priceSettingsList));
    }

    @PostMapping(params = {"pricePerKm"})
    public ResponseEntity<Void> addPriceSettings(@RequestParam(name = "pricePerKm") BigDecimal pricePerKm){
        log.info("Adding new price: " + pricePerKm.toString());
        PriceSettings priceSettings = new PriceSettings(pricePerKm);
        service.savePriceSettings(priceSettings);
        return ResponseEntity.ok().build();
    }
}
