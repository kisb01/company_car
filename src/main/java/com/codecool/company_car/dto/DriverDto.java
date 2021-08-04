package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import com.codecool.company_car.annotation.PastMoreThan18Years;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DriverDto {

    private Long driverId;
    @Name(message = "Name should be at least 2 letters with a capital first letter")
    private String firstName;
    @Name(message = "Name should be at least 2 letters with a capital first letter")
    private String lastName;
    @NotFound
    private Long cityId;
    @PastMoreThan18Years(message = "Birth date should be at least 18 years in the past.")
    private LocalDate birthDate;
}
