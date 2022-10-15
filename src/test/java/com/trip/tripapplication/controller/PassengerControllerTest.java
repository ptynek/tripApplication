package com.trip.tripapplication.controller;

import com.google.gson.Gson;
import com.trip.tripapplication.domain.Passengers;
import com.trip.tripapplication.domain.dto.CreatePassengerDto;
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

import static org.junit.jupiter.api.Assertions.*;

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
        CreatePassengerDto passenger = new CreatePassengerDto(
                "John", "Smith", "112233445", "john.smith@mail.com"
        );
        Gson gson = new Gson();
        String jsonContent = gson.toJson(passenger);
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
    @DisplayName("Get all passengers")
    void testGetAllPassengers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    @DisplayName("Get passenger with specific ID")
    void testGetPassengerWithSpecificId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Smith")));
    }

    @Test
    @DisplayName("Update passenger")
    void testUpdatePassenger() throws Exception{
        Passengers passenger = service.getPassenger(1L);
        CreatePassengerDto passengerDto = mapper.mapToCreatePassengerDto(passenger);
        passengerDto.setLastName("Kovalsky");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(passengerDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Kovalsky")));
    }

}