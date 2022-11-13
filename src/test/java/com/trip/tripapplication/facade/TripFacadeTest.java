package com.trip.tripapplication.facade;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.exceptions.PassengerNotActive;
import com.trip.tripapplication.exceptions.PassengerNotLoggedIn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripFacadeTest {

    @Autowired
    private TripFacade tripFacade;

    @Test
    void testTripFacade(){
        Cities departureCity = new Cities(1L, "Wrocław", "Polska", 50.66857, 17.92253, true);
        Cities arrivalCity = new Cities(2L, "Sanok", "Polska" , 49.5564, 22.21108, true);
        Passengers passenger = new Passengers(1L, "John", "Smith", "332211332", "mail@mail.com", true, true );

        try{
            tripFacade.processRoute(departureCity, arrivalCity, passenger);
        } catch (RouteProcessingExceptions exceptions) {

        } catch (PassengerNotLoggedIn e) {
            throw new RuntimeException(e);
        } catch (PassengerNotActive e) {
            throw new RuntimeException(e);
        }
    }
}