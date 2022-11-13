package com.trip.tripapplication.mapper;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.dto.CitiesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitiesMapper {

    public Cities mapToCities(final CitiesDto citiesDto){
        return new Cities(
                citiesDto.getId(),
                citiesDto.getCity(),
                citiesDto.getCountry(),
                citiesDto.getLatitude(),
                citiesDto.getLongitude(),
                citiesDto.isActive()
        );
    }

    public CitiesDto mapToCitiesDto(final Cities cities){
        return new CitiesDto(
                cities.getId(),
                cities.getCity(),
                cities.getCountry(),
                cities.getLatitude(),
                cities.getLongitude(),
                cities.isActive()
        );
    }

    public List<CitiesDto> mapToCitiesDtoList(final List<Cities> citiesList){
        return citiesList.stream()
                .map(this::mapToCitiesDto)
                .collect(Collectors.toList());
    }

}
