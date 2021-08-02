package com.codecool.company_car.dto;

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
    private String firstName;
    private String lastName;
    private Long cityId;
    private String birthDate;
}
