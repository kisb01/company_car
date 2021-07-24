package com.codecool.company_car.repository;

import com.codecool.company_car.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
