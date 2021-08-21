package com.codecool.company_car.integration;

import com.codecool.company_car.model.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ManufacturerIntegrationTests {

    @LocalServerPort
    private Integer port;

    private String baseUrl;

    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/manufacturer";
    }

    @Test
    public void addNewManufacturer_shouldReturnManufacturerName() {
        Manufacturer testManufacturer = new Manufacturer(null, "Opel");
        Manufacturer result = restTemplate.postForObject(baseUrl, testManufacturer, Manufacturer.class);;
        assertEquals(result.getName(), testManufacturer.getName());
    }

    @Test
    public void getManufacturer_emptyDatabase_returnsEmptyList() {
        List<Manufacturer> Manufacturers = List.of(restTemplate.getForObject(baseUrl, Manufacturer[].class));
        assertEquals(0, Manufacturers.size());
    }

    @Test
    public void getManufacturerById_withOnePostedElement_returnsManufacturerWithSameId() {
        Manufacturer testManufacturer = new Manufacturer(null, "Opel");
        testManufacturer = restTemplate.postForObject(baseUrl, testManufacturer, Manufacturer.class);
        Manufacturer result = restTemplate.getForObject(baseUrl + "/" + testManufacturer.getId(), Manufacturer.class);
        assertEquals(result.getName(), testManufacturer.getName());
    }

    @Test
    public void updateManufacturer_withOnePostedElement_returnUpdatedManufacturer() {
        Manufacturer testManufacturer = new Manufacturer(null, "Opel");
        testManufacturer = restTemplate.postForObject(baseUrl, testManufacturer, Manufacturer.class);

        testManufacturer.setName("Fiat");
        restTemplate.put(baseUrl + "/1", testManufacturer);
        Manufacturer updatedManufacturer = restTemplate.getForObject(baseUrl + "/" + testManufacturer.getId(), Manufacturer.class);

        assertEquals("Fiat", updatedManufacturer.getName());
    }

    @Test
    public void deleteManufacturerById_withSomePostedManufacturers_getAllShouldReturnRemainingManufacturers() {
        Manufacturer testManufacturer1 = new Manufacturer(null, "Opel");
        Manufacturer testManufacturer2 = new Manufacturer(null, "Fiat");
        Manufacturer testManufacturer3 = new Manufacturer(null, "Toyota");
        List<Manufacturer> testManufacturers = new ArrayList<>();
        testManufacturers.add(testManufacturer1);
        testManufacturers.add(testManufacturer2);
        testManufacturers.add(testManufacturer3);

        testManufacturers.forEach(testManufacturer ->
                testManufacturer.setId(restTemplate.postForObject(baseUrl, testManufacturer, Manufacturer.class).getId())
        );

        restTemplate.delete(baseUrl + "/" + testManufacturer2.getId());
        testManufacturers.remove(testManufacturer2);

        List<Manufacturer> remainingManufacturers = List.of(restTemplate.getForObject(baseUrl, Manufacturer[].class));

        assertEquals(testManufacturers.size(), remainingManufacturers.size());
        for (int i = 0; i < testManufacturers.size(); i++) {
            assertEquals(testManufacturers.get(i).getName(), remainingManufacturers.get(i).getName());
        }
    }
}
