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
    private ManufacturerCommand manufacturerCommand;
    private String model;
    private ColorCommand colorCommand;
    private DriverCommand driverCommand;
    private LocalDate inUseSince;
    private Boolean repairRequired;
}
