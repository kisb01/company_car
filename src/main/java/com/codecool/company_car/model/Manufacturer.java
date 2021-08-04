package com.codecool.company_car.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties("companyCars")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "manufacturer")
    private List<CompanyCar> companyCars = new ArrayList<>();

    public Manufacturer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
