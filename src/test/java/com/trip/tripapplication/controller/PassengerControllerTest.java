package com.trip.tripapplication.controller;

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
    private PassengerDbService service;

    @Autowired
    private PassengersMapper mapper;

    @Test
    @DisplayName("Add passenger")
    void testAddPassenger() throws Exception{
        Passengers passengers =  new Passengers(
                1L, "Jan", "Nowak",
                "333222111", "mail@o2.pl", true, true
        );

        PassengersDto passengersDto = mapper.mapToPassengersDto(passengers);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(passengersDto);
        System.out.println(jsonContent);

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
    void testGetPassenger() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }


}