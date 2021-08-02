package com.codecool.company_car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCarDto {

    private Long id;
    private String licencePlateNumber;
    private Long manufacturerId;
    @NotBlank(message = "Model name can not be null")
    private String model;
    private Long colorId;
    private Long driverId;
    @PastOrPresent
    private String inUseSince;
    private Boolean repairRequired = false;
}
