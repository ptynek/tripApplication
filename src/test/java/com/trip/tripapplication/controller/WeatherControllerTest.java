package com.trip.tripapplication.controller;


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
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Get weather by coordinates")
    void testGetWeatherByCoordinates() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather?lat=50.66857&lon=17.92253")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get weather by city id")
    void testGetWeatherByCityId() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}