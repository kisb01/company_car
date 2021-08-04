package com.codecool.company_car.model;

import com.codecool.company_car.annotation.Name;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties("drivers")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "city")
    private List<Driver> drivers = new ArrayList<>();

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
