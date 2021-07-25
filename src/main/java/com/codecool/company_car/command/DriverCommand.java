package com.codecool.company_car.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DriverCommand {

    private Long driverId;
    private String firstName;
    private String lastName;
    private Long cityId;
    private LocalDate birthDate;
}
