package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties("drivers")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Driver> drivers = new HashSet<>();

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
