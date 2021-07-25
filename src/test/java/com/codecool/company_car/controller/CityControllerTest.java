package com.codecool.company_car.controller;

import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityServiceTest {

    @MockBean
    CityService cityService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        City city = new City();
        city.setId(1l);
        city.setName("Budapest");

        when(cityService.findAll()).thenReturn(Set.of(city));

        mockMvc.perform(get("/city")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(city.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(city.getName())));
    }

}