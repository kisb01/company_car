package com.codecool.company_car.controller;

import com.codecool.company_car.model.Color;
import com.codecool.company_car.service.ColorService;
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

@WebMvcTest(ColorController.class)
public class ColorControllerTest {

    @MockBean
    ColorService colorService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        Color color = new Color();
        color.setId(1l);
        color.setName("Red");

        when(colorService.findAll()).thenReturn(Set.of(color));

        mockMvc.perform(get("/color")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(color.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(color.getName())));
    }

    @Test
    public void findById_ShouldReturnFirst() throws Exception {
        Color color = new Color();
        color.setId(1L);
        color.setName("Red");

        when(colorService.findById(1L)).thenReturn(color);

        mockMvc.perform(get("/color/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(color.getId().intValue())))
                .andExpect(jsonPath("$.name", is(color.getName())));

    }

    @Test
    public void add_ShouldReturnOK() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Red");

        mockMvc.perform(MockMvcRequestBuilders.post("/color")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(json))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldReturnOk() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Red");

        mockMvc.perform(MockMvcRequestBuilders.delete("/color/1"))
                .andExpect(status().isOk());
    }

}
