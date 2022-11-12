package com.trip.tripapplication.controller;

import com.google.gson.Gson;
import com.trip.tripapplication.domain.dto.WeatherCodeDto;
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
class WeatherCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get all weather codes")
    void testGetAllWeatherCodes() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather_codes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(28)));
    }

    @Test
    @DisplayName("Add weather code")
    void testAddWeatherCode() throws Exception {
        WeatherCodeDto weahterCode = new WeatherCodeDto(666, "Test weather code");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(weahterCode);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/weather_codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get weather by code")
    void testGetWeatherByCode() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather_codes?weatherCode=666")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.weatherCode", Matchers.is(666)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is(
                        "Test weather code"
                )));
    }
}