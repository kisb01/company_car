package com.codecool.company_car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCarDto {

    private Long id;
    @Pattern(regexp = "([A-Z]{3}[-]*[0-9]{3})")
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
