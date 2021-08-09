package com.codecool.company_car.integration;

import com.codecool.company_car.model.City;
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
public class CityIntegrationTests {

    @LocalServerPort
    private Integer port;

    private String baseUrl;

    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/city";
    }

    @Test
    public void addNewCity_shouldReturnCityName() {
        City testCity = new City(null, "Mád");
        restTemplate.postForObject(baseUrl, testCity, City.class);
        City result = restTemplate.getForObject(baseUrl + "/1", City.class);
        assertEquals(result.getName(), testCity.getName());
    }

    @Test
    public void getCities_emptyDatabase_returnsEmptyList() {
        List<City> cities = List.of(restTemplate.getForObject(baseUrl, City[].class));
        assertEquals(0, cities.size());
    }

    @Test
    public void getCityById_withOnePostedElement_returnsCityWithSameId() {
        City testCity = new City(null, "Budapest");
        restTemplate.postForObject(baseUrl, testCity, City.class);
        City result = restTemplate.getForObject(baseUrl + "/1", City.class);
        assertEquals(testCity.getName(), result.getName());
    }

    @Test
    public void updateCity_withOnePostedElement_returnUpdatedCity() {
        City testCity = new City(null, "Budapest");
        restTemplate.postForObject(baseUrl, testCity, City.class);

        testCity.setName("Eger");
        restTemplate.put(baseUrl + "/1", testCity);
        City updatedCity = restTemplate.getForObject(baseUrl + "/1", City.class);

        assertEquals("Eger", updatedCity.getName());
    }

    @Test
    public void deleteCityById_withSomePostedCities_getAllShouldReturnRemaining() {
        City testCity1 = new City(null, "Budapest");
        City testCity2 = new City(null, "Eger");
        List<City> testCities = new ArrayList<>();
        testCities.add(testCity1);
        testCities.add(testCity2);

        testCities.forEach(testCity ->
                restTemplate.postForObject(baseUrl, testCity, City.class));

        testCity1.setId(restTemplate.getForObject(baseUrl + "/1", City.class).getId());
        testCity2.setId(restTemplate.getForObject(baseUrl + "/2", City.class).getId());

        restTemplate.delete(baseUrl + "/" + testCity2.getId());
        testCities.remove(testCity2);

        List<City> remainingCities = List.of(restTemplate.getForObject(baseUrl, City[].class));
        assertEquals(testCities.size(), remainingCities.size());
    }

}
