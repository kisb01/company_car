package com.codecool.company_car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

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
    @NotFound
    private Long manufacturerId;
    @NotBlank(message = "{companyCar.model}")
    private String model;
    @NotFound
    private Long colorId;
    @NotFound
    private Long driverId;
    @PastOrPresent(message = "{companyCar.inUseSince}")
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
