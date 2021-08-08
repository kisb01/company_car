package com.codecool.company_car.controller;

import com.codecool.company_car.model.Color;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.service.CompanyCarService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyCarController.class)
public class CompanyCarControllerTest {

    @MockBean
    CompanyCarService companyCarService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll_ShouldReturnAll() throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("Opel");
        Color color = new Color();
        color.setId(1L);
        color.setName("Grey");
        Driver driver = new Driver();
        driver.setDriverId(1L);
        CompanyCar companyCar = new CompanyCar();
        companyCar.setId(1L);
        companyCar.setLicencePlateNumber("AOP-979");
        companyCar.setManufacturer(manufacturer);
        companyCar.setModel("Corsa");
        companyCar.setColor(color);
        companyCar.setDriver(driver);
        companyCar.setInUseSince(LocalDate.of(2003,12,12));
        companyCar.setRepairRequired(false);

        when(companyCarService.findAll()).thenReturn(List.of(companyCar));

        mockMvc.perform(get("/companycar")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(companyCar.getId().intValue())))
                .andExpect(jsonPath("$[0].licencePlateNumber", is(companyCar.getLicencePlateNumber())))
                .andExpect(jsonPath("$[0].manufacturer.id", is(companyCar.getManufacturer().getId().intValue())))
                .andExpect(jsonPath("$[0].model", is(companyCar.getModel())))
                .andExpect(jsonPath("$[0].color.id", is(companyCar.getColor().getId().intValue())))
                .andExpect(jsonPath("$[0].driver.driverId", is(companyCar.getDriver().getDriverId().intValue())))
                .andExpect(jsonPath("$[0].inUseSince", is(companyCar.getInUseSince().toString())))
                .andExpect(jsonPath("$[0].repairRequired", is(companyCar.getRepairRequired())));
    }

    @Test
    public void findById_ShouldReturnFirst() throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("Opel");
        Color color = new Color();
        color.setId(1L);
        color.setName("Grey");
        Driver driver = new Driver();
        driver.setDriverId(1L);
        CompanyCar companyCar = new CompanyCar();
        companyCar.setId(1L);
        companyCar.setLicencePlateNumber("AOP-979");
        companyCar.setManufacturer(manufacturer);
        companyCar.setModel("Corsa");
        companyCar.setColor(color);
        companyCar.setDriver(driver);
        companyCar.setInUseSince(LocalDate.of(2003,12,12));
        companyCar.setRepairRequired(false);

        when(companyCarService.findById(1L)).thenReturn(companyCar);

        mockMvc.perform(get("/companycar/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(companyCar.getId().intValue())))
                .andExpect(jsonPath("$.licencePlateNumber", is(companyCar.getLicencePlateNumber())))
                .andExpect(jsonPath("$.manufacturer.id", is(companyCar.getManufacturer().getId().intValue())))
                .andExpect(jsonPath("$.model", is(companyCar.getModel())))
                .andExpect(jsonPath("$.color.id", is(companyCar.getColor().getId().intValue())))
                .andExpect(jsonPath("$.driver.driverId", is(companyCar.getDriver().getDriverId().intValue())))
                .andExpect(jsonPath("$.inUseSince", is(companyCar.getInUseSince().toString())))
                .andExpect(jsonPath("$.repairRequired", is(companyCar.getRepairRequired())));
    }

    @Test
    public void findById_ShouldReturnException() throws Exception {
        mockMvc.perform(get("/companycar/a")).andExpect(status().isBadRequest());
    }

    @Test
    public void add_ShouldReturnOK() throws Exception {
        JSONObject companyCar = new JSONObject();
        JSONObject manufacturer = new JSONObject();
        manufacturer.put("id", 1);
        manufacturer.put("name", "Opel");
        JSONObject color = new JSONObject();
        color.put("id", 1);
        color.put("name", "Grey");
        JSONObject driver = new JSONObject();
        driver.put("id", 1);
        companyCar.put("id", 1);
        companyCar.put("licencePlateNumber", "IIL-215");
        companyCar.put("manufacturer", manufacturer);
        companyCar.put("model", "Corsa");
        companyCar.put("color", color);
        companyCar.put("driver", driver);
        companyCar.put("inUseDate", LocalDate.of(2003,12,12));
        companyCar.put("repairRequired", false);


        mockMvc.perform(MockMvcRequestBuilders.post("/companycar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(companyCar))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add_ShouldReturnException() throws Exception {
        JSONObject companyCar = new JSONObject();
        JSONObject manufacturer = new JSONObject();
        manufacturer.put("id", 1);
        manufacturer.put("name", "Opel");
        JSONObject color = new JSONObject();
        color.put("id", 1);
        color.put("name", "Grey");
        JSONObject driver = new JSONObject();
        driver.put("id", 1);
        companyCar.put("id", 1);
        companyCar.put("licencePlateNumber", "IIL-215");
        companyCar.put("manufacturer", manufacturer);
        companyCar.put("model", "");
        companyCar.put("color", color);
        companyCar.put("driver", driver);
        companyCar.put("inUseDate", LocalDate.of(2003,12,12));
        companyCar.put("repairRequired", false);


        mockMvc.perform(MockMvcRequestBuilders.post("/companycar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(companyCar))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_ShouldReturnOK() throws Exception {
        JSONObject companyCar = new JSONObject();
        JSONObject manufacturer = new JSONObject();
        manufacturer.put("id", 1);
        manufacturer.put("name", "Opel");
        JSONObject color = new JSONObject();
        color.put("id", 1);
        color.put("name", "Grey");
        JSONObject driver = new JSONObject();
        driver.put("id", 1);
        companyCar.put("id", 1);
        companyCar.put("licencePlateNumber", "IIL-215");
        companyCar.put("manufacturer", manufacturer);
        companyCar.put("model", "Corsa");
        companyCar.put("color", color);
        companyCar.put("driver", driver);
        companyCar.put("inUseDate", LocalDate.of(2003,12,12));
        companyCar.put("repairRequired", false);


        mockMvc.perform(MockMvcRequestBuilders.put("/companycar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(companyCar))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldReturnOk() throws Exception {
        JSONObject companyCar = new JSONObject();
        JSONObject manufacturer = new JSONObject();
        manufacturer.put("id", 1);
        manufacturer.put("name", "Opel");
        JSONObject color = new JSONObject();
        color.put("id", 1);
        color.put("name", "Grey");
        JSONObject driver = new JSONObject();
        driver.put("id", 1);
        companyCar.put("id", 1);
        companyCar.put("licencePlateNumber", "IIL-215");
        companyCar.put("manufacturer", manufacturer);
        companyCar.put("model", "Corsa");
        companyCar.put("color", color);
        companyCar.put("driver", driver);
        companyCar.put("inUseDate", LocalDate.of(2003,12,12));
        companyCar.put("repairRequired", false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/companycar/1"))
                .andExpect(status().isOk());
    }
}
