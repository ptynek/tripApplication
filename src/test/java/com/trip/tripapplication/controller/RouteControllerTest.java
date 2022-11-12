package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.Cities;
import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CitiesDto;
import com.trip.tripapplication.domain.dto.PassengersDto;
import com.trip.tripapplication.service.CitiesDbService;
import com.trip.tripapplication.service.PassengerDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CitiesDbService citiesDbService;
    @Autowired
    private PassengerDbService passengerDbService;


    @Test
    @DisplayName("Create new route")
    void testCreateNewRoute() throws Exception {
        Cities departureCity = new Cities("Opole", "Poland", 50.66952, 17.92018, true);
        Cities arrivalCity = new Cities("Sanok", "Poland", 49.55640, 22.21108, true);
        Passengers passenger = new Passengers("John", "Smith", "500300400", "mail@mail.com", true, true);
        LocalDateTime dateTime = LocalDateTime.now();

        citiesDbService.saveCity(departureCity);
        long departureCityId = departureCity.getId();
        citiesDbService.saveCity(arrivalCity);
        long arricalCityId = arrivalCity.getId();
        passengerDbService.savePassenger(passenger);
        long passengerId = passenger.getId();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/passengerlogin/" + passengerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/route?idCityFrom=" + departureCityId + "&idCityTo=" + arricalCityId
                        + "&dateTime=" + dateTime)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Get all routes")
    void testGetAllRoutes() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/route")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}