package com.codecool.company_car;

import com.codecool.company_car.model.*;
import org.junit.jupiter.api.Assertions;
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
    public void testAllFromACity() {
        List<Driver> expected = new ArrayList<>() {{
            add(new Driver(1L, "Eszter Enikő", "Biró-Kányási", new City(4L, "Miskolc"), LocalDate.of(1993, 9, 17)));
        }};
        ResponseEntity<Driver[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/driver/name?city=Miskolc", Driver[].class);
        List<Driver> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    public void testFindByDriver() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};

        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/driver?name=Eszter", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllByManufacturer() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/manufacturer?name=Opel", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllByColor() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/color?name=Grey", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllNeedsRepair() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(4L, "FPP-187", new Manufacturer(1L, "BMW"), "X3", new Color(2L, "Red"),
                    new Driver(), LocalDate.of(2011, 4, 23), true));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/repair", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }

    @Test
    public void testFindAllInCity() {
        List<CompanyCar> expected = new ArrayList<>() {{
            add(new CompanyCar(1L, "IIL-215", new Manufacturer(1L, "Opel"), "Corsa", new Color(4L, "Grey"),
                    new Driver(), LocalDate.of(2002, 12, 5), false));
        }};
        ResponseEntity<CompanyCar[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/companycar/city?name=Miskolc", CompanyCar[].class);
        List<CompanyCar> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected.get(0).getLicencePlateNumber(), actual.get(0).getLicencePlateNumber());
    }
}