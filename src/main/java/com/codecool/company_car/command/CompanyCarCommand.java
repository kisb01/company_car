package com.codecool.company_car.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCarCommand {

    private Long id;
    private String licencePlateNumber;
    private Long manufacturerId;
    private String model;
    private Long colorId;
    private Long driverId;
    private LocalDate inUseSince;
    private Boolean repairRequired;
}
