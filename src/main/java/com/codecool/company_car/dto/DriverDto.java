package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import com.codecool.company_car.annotation.PastMoreThan18Years;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DriverDto {

    private Long driverId;
    @Name(message = "{driver.firstName}")
    private String firstName;
    @Name(message = "{driver.lastName}")
    private String lastName;
    @Min(value = 1, message = "City id must be greater than zero")
    @NotFound
    private Long cityId;
    @PastMoreThan18Years(message = "{driver.birthDate}")
    private LocalDate birthDate;
}
