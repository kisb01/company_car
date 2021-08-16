package com.codecool.company_car.integration;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        DriverDto testDriver = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, "1993-09-17");
        DriverDto result = restTemplate.postForObject(baseUrl, testDriver, DriverDto.class);
        assertEquals(result.getFirstName(), testDriver.getFirstName());
    }

    @Test
    public void getDriver_originalDatabase_returnsFullListSize() {
        List<Driver> drivers = List.of(restTemplate.getForObject(baseUrl, Driver[].class));
        assertEquals(3, drivers.size());
    }

    @Test
    public void getDriverById_withOnePostedElement_returnsDriverWithSameId() {
        DriverDto testDriver = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, "1993-09-17");
        Driver expectDriver = restTemplate.postForObject(baseUrl, testDriver, Driver.class);
        Driver result = restTemplate.getForObject(baseUrl + "/" + expectDriver.getDriverId(), Driver.class);
        assertEquals(result.getFirstName(), expectDriver.getFirstName());
    }
}
