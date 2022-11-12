package com.trip.tripapplication.controller;

import com.trip.tripapplication.domain.dto.PriceSettingsDto;
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

@SpringBootTest
@AutoConfigureMockMvc
class PriceSettingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Add price per kilometer")
    void testaddPricePerKm() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/priceSettings?pricePerKm=1.10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get all prices")
    void testGetAllPrices() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/priceSettings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }
}