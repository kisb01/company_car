package com.codecool.company_car.repository;

import com.codecool.company_car.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
