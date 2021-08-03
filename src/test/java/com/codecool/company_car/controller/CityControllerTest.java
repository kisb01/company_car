package com.codecool.company_car.controller;

import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @MockBean
    CityService cityService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        City city = new City();
        city.setId(1l);
        city.setName("Budapest");

        when(cityService.findAll()).thenReturn(List.of(city));

        mockMvc.perform(get("/city")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(city.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(city.getName())));
    }

    @Test
    public void findById_ShouldReturnFirst() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setName("Budapest");

        when(cityService.findById(1L)).thenReturn(city);

        mockMvc.perform(get("/city/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(city.getId().intValue())))
                .andExpect(jsonPath("$.name", is(city.getName())));
    }

    @Test
    public void add_ShouldReturnOK() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Budapest");


        mockMvc.perform(MockMvcRequestBuilders.post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(json))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void update_ShouldReturnOK() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Budapest");

        mockMvc.perform(MockMvcRequestBuilders.put("/city/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(json))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldReturnOk() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Budapest");

        mockMvc.perform(MockMvcRequestBuilders.delete("/city/1"))
                .andExpect(status().isOk());
    }

}