package com.trip.tripapplication.controller;

import com.google.gson.Gson;
import com.trip.tripapplication.domain.dto.CitiesDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
class CitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Add city")
    void testAddCity() throws Exception{
        CitiesDto citiesDto = new CitiesDto("Wrocław", "Poland",
                50.66857, 17.92253);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(citiesDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get all cities")
    void testGetAllCities() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    @DisplayName("Get city with specific id")
    void testGetCityWithSpecificId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("Wrocław")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", Matchers.is("Poland")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude", Matchers.is(50.66857)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude", Matchers.is(17.92253)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    @DisplayName("Get city by name")
    void testGetCityByName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/cities/name?city=Wrocław")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("Wrocław")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", Matchers.is("Poland")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude", Matchers.is(50.66857)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude", Matchers.is(17.92253)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active", Matchers.is(true)));
    }

    @Test
    @DisplayName("Update city")
    void testUpdateCity() throws Exception {
        CitiesDto citiesDto = new CitiesDto(1, "Wrocław", "Polska",
                50.66857, 17.92253, true);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(citiesDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", Matchers.is("Polska")));
    }

    @Test
    @DisplayName("Deactivate city")
    void testDeactivateCity() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/cities/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}