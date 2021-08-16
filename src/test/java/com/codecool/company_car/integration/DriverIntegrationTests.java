package com.codecool.company_car.integration;

import com.codecool.company_car.dto.DriverDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DriverDto testDriver = new DriverDto(null, "Eszter Enikő", "Bíró-Kányási", 1L, LocalDate.of(1993, 9, 17));
        DriverDto result = restTemplate.postForObject(baseUrl, testDriver, DriverDto.class);
        assertEquals(result.getFirstName(), testDriver.getFirstName());
    }
}
