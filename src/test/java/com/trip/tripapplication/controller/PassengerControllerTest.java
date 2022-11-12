package com.trip.tripapplication.controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.google.gson.Gson;
import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.PassengersDto;
import com.trip.tripapplication.mapper.PassengersMapper;
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

@SpringBootTest
@AutoConfigureMockMvc
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PassengersMapper mapper;

    @Test
    @DisplayName("Add passenger")
    void testAddPassenger() throws Exception {
        Passengers passengers =  new Passengers(
                1L, "Jan", "Nowak",
                "333222111", "mail@o2.pl", false, false
        );

        PassengersDto passengersDto = mapper.mapToPassengersDto(passengers);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(passengersDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get passengers")
    void testGetAllPassengers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    @DisplayName("Activate passenger")
    void testActivatePassenger() throws Exception {
        PassengersDto passenger = new PassengersDto(
                1, "Jan", "Nowak",
                "333222111", "mail@o2.pl", true, false);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(passenger);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get passenger")
    void testGetPassenger() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/passengers/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Nowak")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is("333222111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mail", Matchers.is("mail@o2.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.loggedIn", Matchers.is(false)));
    }

    @Test
    @DisplayName("Deactivate passenger")
    void testDeactivatePassenger() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/passengers/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}