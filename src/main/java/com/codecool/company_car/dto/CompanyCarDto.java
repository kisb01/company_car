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
    @Pattern(regexp = "([A-Z]{3}[-]*[0-9]{3})", message = "Default Hungarian licence plate pattern: ABC-123")
    private String licencePlateNumber;
    @NotFound
    private Long manufacturerId;
    @NotBlank(message = "Model name can not be null")
    private String model;
    @NotFound
    private Long colorId;
    @NotFound
    private Long driverId;
    @PastOrPresent(message = "Date should be present date or before it")
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
