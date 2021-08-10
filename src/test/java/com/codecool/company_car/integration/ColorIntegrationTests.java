package com.codecool.company_car.integration;

import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.model.Color;
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
public class ColorIntegrationTests {

    @LocalServerPort
    private Integer port;

    private String baseUrl;

    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/color";
    }

    @Test
    public void addNewColor_shouldReturnColorName() {
        ColorDto testColor = new ColorDto(null, "Red");
        ColorDto result = restTemplate.postForObject(baseUrl, testColor, ColorDto.class);;
        assertEquals(result.getName(), testColor.getName());
    }

    @Test
    public void getColor_emptyDatabase_returnsEmptyList() {
        List<Color> colors = List.of(restTemplate.getForObject(baseUrl, Color[].class));
        assertEquals(0, colors.size());
    }

    @Test
    public void getColorById_withOnePostedElement_returnsColorWithSameId() {
        Color testColor = new Color(null, "Red");
        testColor = restTemplate.postForObject(baseUrl, testColor, Color.class);
        Color result = restTemplate.getForObject(baseUrl + "/" + testColor.getId(), Color.class);
        assertEquals(result.getName(), testColor.getName());
    }

    @Test
    public void updateColor_withOnePostedElement_returnUpdatedColor() {
        Color testColor = new Color(null, "Red");
        testColor = restTemplate.postForObject(baseUrl, testColor, Color.class);

        testColor.setName("Green");
        restTemplate.put(baseUrl + "/1", testColor);
        Color updatedColor = restTemplate.getForObject(baseUrl + "/" + testColor.getId(), Color.class);

        assertEquals("Green", updatedColor.getName());
    }

    @Test
    public void deleteColorById_withSomePostedColors_getAllShouldReturnRemainingColors() {
        Color testColor1 = new Color(null, "Red");
        Color testColor2 = new Color(null, "Green");
        Color testColor3 = new Color(null, "Blue");
        List<Color> testColors = new ArrayList<>();
        testColors.add(testColor1);
        testColors.add(testColor2);
        testColors.add(testColor3);

        testColors.forEach(testColor ->
                testColor.setId(restTemplate.postForObject(baseUrl, testColor, Color.class).getId())
        );

        restTemplate.delete(baseUrl + "/" + testColor2.getId());
        testColors.remove(testColor2);

        List<Color> remainingColors = List.of(restTemplate.getForObject(baseUrl, Color[].class));

        assertEquals(testColors.size(), remainingColors.size());
        for(int i = 0; i< testColors.size(); i++){
            assertEquals(testColors.get(i).getName(), remainingColors.get(i).getName());
        }
    }
}
