package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.PriceSettings;
import com.trip.tripapplication.domain.dto.PriceSettingsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PriceSettingsMapper {

    public PriceSettingsDto mapToPriceSettingsDto(final PriceSettings priceSettings){
        return new PriceSettingsDto(
                priceSettings.getId(),
                priceSettings.getDateOfModification(),
                priceSettings.getPricePerKilometer()
        );
    }

    public List<PriceSettingsDto> mapToPriceSettingsDtoList(final List<PriceSettings> priceSettingsList){
        return priceSettingsList.stream()
                .map(this::mapToPriceSettingsDto)
                .collect(Collectors.toList());
    }
}
