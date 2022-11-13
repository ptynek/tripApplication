package com.trip.tripapplication.facade;


import com.trip.tripapplication.client.tomtom.TomTomClient;
import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.exceptions.PassengerNotActive;
import com.trip.tripapplication.exceptions.PassengerNotLoggedIn;
import com.trip.tripapplication.mapper.CitiesMapper;
import com.trip.tripapplication.service.CitiesDbService;
import com.trip.tripapplication.service.PassengerDbService;
import com.trip.tripapplication.service.RouteDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripFacade.class);
    private final CitiesDbService citiesDbService;
    private final CitiesMapper citiesMapper;
    private final RouteDbService routeDbService;
    private final TomTomClient tomTomClient;

    @Autowired
    public TripFacade(CitiesDbService citiesDbService, CitiesMapper citiesMapper, RouteDbService routeDbService, TomTomClient tomTomClient) {
        this.citiesDbService = citiesDbService;
        this.citiesMapper = citiesMapper;
        this.routeDbService = routeDbService;
        this.tomTomClient = tomTomClient;
    }

    public void processRoute(final Cities departureCity, final Cities arrivalCity, final Passengers passenger) throws RouteProcessingExceptions, PassengerNotLoggedIn, PassengerNotActive {

        boolean wasError = false;

        LOGGER.info("Checking if cities are existing");
        if(citiesDbService.checkIfCityIsActive(departureCity, arrivalCity)){
            LOGGER.error(RouteProcessingExceptions.ERR_CITY_NOT_ACTIVE);
            wasError = true;
            throw new RouteProcessingExceptions(RouteProcessingExceptions.ERR_CITY_NOT_ACTIVE);
        }

        try{
            CitiesDto departureCityDto = citiesMapper.mapToCitiesDto(departureCity);
            CitiesDto arrivalCityDto = citiesMapper.mapToCitiesDto(arrivalCity);
            tomTomClient.getRoute(departureCityDto, arrivalCityDto);
            LOGGER.info("Creating route from " + departureCity.getCity() + " to " + arrivalCity.getCity());

            if(!routeDbService.checkIfIsLoggedIn()){
                LOGGER.error(RouteProcessingExceptions.ERR_PASSENGER_NOT_LOGGED_IN);
                wasError = true;
                throw new RouteProcessingExceptions(RouteProcessingExceptions.ERR_PASSENGER_NOT_LOGGED_IN);
            }
            if(!passenger.isActive()){
                LOGGER.error(RouteProcessingExceptions.ERR_PASSENGER_NOT_ACTIVE);
                wasError = true;
                throw new RouteProcessingExceptions(RouteProcessingExceptions.ERR_PASSENGER_NOT_ACTIVE);
            }

            LOGGER.info("Route created!");
        } finally {
            if(wasError){
                LOGGER.error("Cancelling creating route!");
            }
        }
    }

}
