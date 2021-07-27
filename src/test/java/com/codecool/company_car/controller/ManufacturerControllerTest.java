package com.codecool.company_car.controller;

import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.service.ManufacturerService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManufacturerController.class)
public class ManufacturerControllerTest {

    @MockBean
    ManufacturerService manufacturerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1l);
        manufacturer.setName("Opel");

        when(manufacturerService.findAll()).thenReturn(Set.of(manufacturer));

        mockMvc.perform(get("/manufacturer")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(manufacturer.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(manufacturer.getName())));
    }

    @Test
    public void findById_ShouldReturnFirst() throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1l);
        manufacturer.setName("Opel");

        when(manufacturerService.findById(1L)).thenReturn(manufacturer);

        mockMvc.perform(get("/manufacturer/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(manufacturer.getId().intValue())))
                .andExpect(jsonPath("$.name", is(manufacturer.getName())));
    }

    @Test
    public void add_ShouldReturnOK() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Opel");


        mockMvc.perform(MockMvcRequestBuilders.post("/manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(json))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldReturnOk() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Opel");

        mockMvc.perform(MockMvcRequestBuilders.delete("/manufacturer/1"))
                .andExpect(status().isOk());
    }
}