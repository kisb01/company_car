package com.codecool.company_car.integration;

import com.codecool.company_car.dto.CompanyCarDto;
import com.codecool.company_car.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyCarIntegrationTests {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port;
    }

    @Test
    public void addNewCompanyCar_shouldReturnCompanyCarName() {
        CompanyCarDto testCompanyCar = new CompanyCarDto(null, "RAP-949", 2L, "SuperB", 3L, 4L, LocalDate.of(2020, 12, 12), false);
        CompanyCarDto result = restTemplate.postForObject(BASE_URL + "/companycar", testCompanyCar, CompanyCarDto.class);
        assertEquals(result.getLicencePlateNumber(), testCompanyCar.getLicencePlateNumber());
    }

    @Test
    public void getCompanyCar_returnsFullList() {
        List<CompanyCar> companyCars = List.of(restTemplate.getForObject(BASE_URL + "/companycar", CompanyCar[].class));
        assertEquals(5, companyCars.size());
    }

    @Test
    public void getCompanyCarById_withOnePostedElement_returnsCompanyCarWithSameId() {
        CompanyCarDto testCompanyCar = new CompanyCarDto(null, "RAP-949", 2L, "SuperB", 3L, null, LocalDate.of(2020, 12, 12), false);
        testCompanyCar = restTemplate.postForObject(BASE_URL + "/companycar", testCompanyCar, CompanyCarDto.class);
        CompanyCar result = restTemplate.getForObject(BASE_URL + "/companycar/" + testCompanyCar.getId(), CompanyCar.class);
        assertEquals(result.getLicencePlateNumber(), testCompanyCar.getLicencePlateNumber());
    }

    @Test
    public void updateCompanyCar_withOnePostedElement_returnUpdatedCompanyCar() {
        CompanyCarDto testCompanyCar = new CompanyCarDto(null, "RAP-949", 2L, "SuperB", 3L, null, LocalDate.of(2020, 12, 12), false);
        testCompanyCar = restTemplate.postForObject(BASE_URL + "/companycar", testCompanyCar, CompanyCarDto.class);

        testCompanyCar.setLicencePlateNumber("AOL-123");
        restTemplate.put(BASE_URL + "/companycar/" + testCompanyCar.getId(), testCompanyCar);
        CompanyCar updatedCompanyCar = restTemplate.getForObject(BASE_URL + "/companycar/" + testCompanyCar.getId(), CompanyCar.class);

        assertEquals("AOL-123", updatedCompanyCar.getLicencePlateNumber());
    }

    @Test
    public void deleteCompanyCarById_withCompanyCarsInDatabase_getAllShouldReturnRemaingCompanyCars() {
        List<CompanyCar> companyCars = new ArrayList<>(List.of(restTemplate.getForObject(BASE_URL + "/companycar", CompanyCar[].class)));

        restTemplate.delete(BASE_URL + "/companycar/" + companyCars.get(0).getId());
        companyCars.remove(0);

        List<CompanyCar> remainingCompanyCars = List.of(restTemplate.getForObject(BASE_URL + "/companycar", CompanyCar[].class));

        for (int i = 0; i < companyCars.size(); i++) {
            assertEquals(companyCars.get(i).getLicencePlateNumber(), remainingCompanyCars.get(i).getLicencePlateNumber());
        }
        assertEquals(companyCars.size(), remainingCompanyCars.size());
    }

    @Test
    public void testAllFromACity() {
        List<Driver> expected = new ArrayList<>() {{
            add(new Driver(1L, "Eszter Enikő", "Biró-Kányási", new City(4L, "Miskolc"), LocalDate.of(1993, 9, 17)));
        }};
        ResponseEntity<Driver[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/driver/name?city=Miskolc", Driver[].class);
        List<Driver> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    public void testFindByDriver() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};

        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/driver?name=Eszter", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllByManufacturer() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/manufacturer?name=Opel", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllByColor() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/color?name=Grey", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllNeedsRepair() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(4L, "FPP-187", new Manufacturer(1L, "BMW"), "X3", new Color(2L, "Red"),
                    new Driver(), LocalDate.of(2011, 4, 23), true));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/repair", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllInCity() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/city?name=Miskolc", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

}
