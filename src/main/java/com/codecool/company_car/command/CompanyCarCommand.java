package com.codecool.company_car.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCarCommand {

    private Long id;
    private String licencePlateNumber;
    private Long manufacturerId;
    @NotBlank(message = "Model name can not be null")
    private String model;
    private Long colorId;
    private Long driverId;
    @PastOrPresent
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
