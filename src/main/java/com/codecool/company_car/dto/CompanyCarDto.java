package com.codecool.company_car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCarDto {

    private Long id;
    @Pattern(regexp = "([A-Z]{3}[-]*[0-9]{3})", message = "{companyCar.licencePlateNumber}")
    private String licencePlateNumber;
    @Min(value = 1, message = "Manufacturer id must be greater than zero")
    private Long manufacturerId;
    @NotBlank(message = "{companyCar.model}")
    private String model;
    @Min(value = 1, message = "Color id must be greater than zero")
    private Long colorId;
    private Long driverId;
    @PastOrPresent(message = "{companyCar.inUseSince}")
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
