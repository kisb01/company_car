package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@JsonIgnoreProperties("driver")
public class CompanyCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licencePlateNumber;
    @ManyToOne
    private Manufacturer manufacturer;
    private String model;
    @ManyToOne
    private Color color;
    @OneToOne(mappedBy = "companyCar")
    private Driver driver;
    private LocalDate inUseSince;
    private Boolean repairRequired;
}
