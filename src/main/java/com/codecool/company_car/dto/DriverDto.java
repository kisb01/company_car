package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import com.codecool.company_car.annotation.PastMoreThan18Years;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DriverDto {

    private Long driverId;
    @Name(message = "Name can not be null")
    private String firstName;
    @Name(message = "Name can not be null")
    private String lastName;
    private Long cityId;
    @PastMoreThan18Years
    private LocalDate birthDate;
}
