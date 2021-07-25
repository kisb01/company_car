package com.codecool.company_car.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @OneToOne
    private CompanyCar companyCar;
    @ManyToOne
    private City city;
    private LocalDate birthDate;

}
