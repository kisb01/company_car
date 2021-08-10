package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "driver")
    private CompanyCar companyCar;
    @ManyToOne
    private City city;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public Driver(Long driverId, String firstName, String lastName, City city, LocalDate birthDate) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.birthDate = birthDate;
    }
}
