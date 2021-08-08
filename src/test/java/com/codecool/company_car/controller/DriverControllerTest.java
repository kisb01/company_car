package com.codecool.company_car.controller;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.CityService;
import com.codecool.company_car.service.DriverService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DriverController.class)
public class DriverControllerTest {

    @MockBean
    DriverService driverService;
    @MockBean
    CityService cityService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        City city = new City();
        city.setId(1l);
        city.setName("Budapest");
        Driver driver = new Driver();
        driver.setDriverId(1L);
        driver.setCity(city);
        driver.setFirstName("János");
        driver.setLastName("Bíró");
        driver.setBirthDate(LocalDate.of(1984, 6, 14));

        when(driverService.findAll()).thenReturn(List.of(driver));

        mockMvc.perform(get("/driver")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].driverId", is(driver.getDriverId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(driver.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(driver.getLastName())))
                .andExpect(jsonPath("$[0].city.id", is(driver.getCity().getId().intValue())))
                .andExpect(jsonPath("$[0].birthDate", is(driver.getBirthDate().toString())));
    }

    @Test
    public void findById_ShouldReturnFirst() throws Exception {
        City city = new City();
        city.setId(1l);
        city.setName("Budapest");
        Driver driver = new Driver();
        driver.setDriverId(1L);
        driver.setCity(city);
        driver.setFirstName("János");
        driver.setLastName("Bíró");
        driver.setBirthDate(LocalDate.of(1984, 6, 14));

        when(driverService.findById(1L)).thenReturn(driver);

        mockMvc.perform(get("/driver/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.driverId", is(driver.getDriverId().intValue())))
                .andExpect(jsonPath("$.firstName", is(driver.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(driver.getLastName())))
                .andExpect(jsonPath("$.city.id", is(driver.getCity().getId().intValue())))
                .andExpect(jsonPath("$.birthDate", is(driver.getBirthDate().toString())));
    }

    @Test
    public void findById_ShouldReturnException() throws Exception {
        mockMvc.perform(get("/driver/a")).andExpect(status().isBadRequest());
    }


    @Test
    public void add_ShouldReturnOK() throws Exception {
        JSONObject driver = new JSONObject();
        JSONObject city = new JSONObject();
        city.put("id", 1);
        city.put("name", "Miskolc");
        driver.put("driverId", 1);
        driver.put("firstName", "János");
        driver.put("lastName", "Bíró");
        driver.put("city", city);
        driver.put("birthDate", LocalDate.of(1984, 6, 14));

        mockMvc.perform(MockMvcRequestBuilders.post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(driver))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add_ShouldReturnException() throws Exception {
        JSONObject driver = new JSONObject();
        JSONObject city = new JSONObject();
        city.put("id", 1);
        city.put("name", "Miskolc");
        driver.put("driverId", 1);
        driver.put("firstName", "jani");
        driver.put("lastName", "Bíró");
        driver.put("city", city);
        driver.put("birthDate", LocalDate.of(1984, 6, 14));

        mockMvc.perform(MockMvcRequestBuilders.post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(driver))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_ShouldReturnOK() throws Exception {
        JSONObject driver = new JSONObject();
        JSONObject city = new JSONObject();
        city.put("id", 1);
        city.put("name", "Miskolc");
        driver.put("driverId", 1);
        driver.put("firstName", "János");
        driver.put("lastName", "Bíró");
        driver.put("city", city);
        driver.put("birthDate", LocalDate.of(1984, 6, 14));

        mockMvc.perform(MockMvcRequestBuilders.put("/driver/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(driver))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldReturnOk() throws Exception {
        JSONObject driver = new JSONObject();
        JSONObject city = new JSONObject();
        city.put("id", 1);
        city.put("name", "Miskolc");
        driver.put("driverId", 1);
        driver.put("firstName", "János");
        driver.put("lastName", "Bíró");
        driver.put("city", city);
        driver.put("birthDate", LocalDate.of(1984, 6, 14));

        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/1"))
                .andExpect(status().isOk());
    }
}
