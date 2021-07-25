package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties("companyCar")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToOne(mappedBy = "driver")
    private CompanyCar companyCar;
    @ManyToOne
    private City city;
    @Past
    private LocalDate birthDate;

}
