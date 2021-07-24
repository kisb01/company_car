package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties("driver")
public class CompanyCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "([A-Z]{3}[-]*[0-9]{3})")
    private String licencePlateNumber;

    @ManyToOne
    private Manufacturer manufacturer;
    private String model;

    @ManyToOne
    private Color color;

    @OneToOne(mappedBy = "companyCar")
    private Driver driver;
    private LocalDate inUseSince;
    private Boolean repairRequired = false;
}
