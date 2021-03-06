package com.codecool.company_car.dto;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CompanyCarDto {

    private Long id;
    @Pattern(regexp = "([A-Z]{3}[-]*[0-9]{3})", message = "{companyCar.licencePlateNumber}")
    private String licencePlateNumber;
    @Min(value = 1, message = "{companyCar.manufacturerId}")
    private Long manufacturerId;
    @NotBlank(message = "{companyCar.model}")
    private String model;
    @Min(value = 1, message = "{companyCar.colorId}")
    private Long colorId;
    private Long driverId;
    @PastOrPresent(message = "{companyCar.inUseSince}")
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
