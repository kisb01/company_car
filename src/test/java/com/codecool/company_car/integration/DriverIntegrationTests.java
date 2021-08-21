package com.codecool.company_car.integration;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.exception.DriverNotFoundException;
import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class DriverIntegrationTests {

    @LocalServerPort
    private Integer port;

    private String baseUrl;

    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/driver";
    }

    @Test
    public void addNewDriver_shouldReturnDriverName() {
        CityDto testCity = new CityDto(null, "Miskolc");
        restTemplate.postForObject("http://localhost:" + port + "/city", testCity, City.class);
        DriverDto testDriver = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, "1993-09-17");
        DriverDto result = restTemplate.postForObject(baseUrl, testDriver, DriverDto.class);
        assertEquals(result.getFirstName(), testDriver.getFirstName());
    }

    @Test
    public void getDriver_emptyDatabase_returnsEmptyList() {
        List<Driver> drivers = List.of(restTemplate.getForObject(baseUrl, Driver[].class));
        assertEquals(0, drivers.size());
    }

    @Test
    public void getDriverById_withOnePostedElement_returnsDriverWithSameId() {
        CityDto testCity = new CityDto(null, "Miskolc");
        restTemplate.postForObject("http://localhost:" + port + "/city", testCity, City.class);
        DriverDto testDriver = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, "1993-09-17");
        Driver expectDriver = restTemplate.postForObject(baseUrl, testDriver, Driver.class);
        Driver result = restTemplate.getForObject(baseUrl + "/" + expectDriver.getDriverId(), Driver.class);
        assertEquals(result.getFirstName(), expectDriver.getFirstName());
    }

    @Test
    public void updateDriver_withOnePostedElement_returnUpdatedDriver() {
        CityDto testCity = new CityDto(null, "Miskolc");
        restTemplate.postForObject("http://localhost:" + port + "/city", testCity, City.class);
        DriverDto driverDto = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, "1993-09-17");
        Driver testDriver = restTemplate.postForObject(baseUrl, driverDto, Driver.class);

        driverDto.setFirstName("János");
        restTemplate.put(baseUrl + "/1", driverDto);
        Driver updatedDriver = restTemplate.getForObject(baseUrl + "/" + testDriver.getDriverId(), Driver.class);

        assertEquals("János", updatedDriver.getFirstName());
    }

    @Test
    public void deleteDriverById_withSomePostedDrivers_getAllShouldReturnRemainingDrivers() {
        CityDto testCity = new CityDto(null, "Miskolc");
        restTemplate.postForObject("http://localhost:" + port + "/city", testCity, City.class);
        DriverDto driverDto1 = new DriverDto(null, "Adorján", "Veres", 1L, "2000-04-17");
        DriverDto driverDto2 = new DriverDto(null, "János", "Bíró", 1L, "1984-06-14");
        DriverDto driverDto3 = new DriverDto(null, "Zsolt", "Balázs", 1L, "1990-07-28");
        List<DriverDto> testDrivers = new ArrayList<>();
        testDrivers.add(driverDto1);
        testDrivers.add(driverDto2);
        testDrivers.add(driverDto3);

        testDrivers.forEach(td ->
                td.setDriverId(restTemplate.postForObject(baseUrl, td, Driver.class).getDriverId())
        );

        restTemplate.delete(baseUrl + "/" + driverDto2.getDriverId());
        testDrivers.remove(driverDto2);

        List<Driver> remainingDrivers = List.of(restTemplate.getForObject(baseUrl, Driver[].class));

        for (int i = 0; i < testDrivers.size(); i++) {
            assertEquals(testDrivers.get(i).getFirstName(), remainingDrivers.get(i).getFirstName());
        }
        assertEquals(testDrivers.size(), remainingDrivers.size());
    }
}
