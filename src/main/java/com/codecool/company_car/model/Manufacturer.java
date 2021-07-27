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
@JsonIgnoreProperties("companyCars")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private Set<CompanyCar> companyCars = new HashSet<>();

    public Manufacturer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
