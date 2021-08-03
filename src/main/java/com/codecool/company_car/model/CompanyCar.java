package com.codecool.company_car.model;

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

    @OneToOne
    private Driver driver;
    private LocalDate inUseSince;
    private Boolean repairRequired = false;

    public CompanyCar(Long id, String licencePlateNumber, Manufacturer manufacturer, String model, Color color, Driver driver, LocalDate inUseSince, Boolean repairRequired) {
        this.id = id;
        this.licencePlateNumber = licencePlateNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.driver = driver;
        this.inUseSince = inUseSince;
        this.repairRequired = repairRequired;
    }
}
