package com.codecool.company_car.repository;

import com.codecool.company_car.model.CompanyCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyCarRepository extends JpaRepository<CompanyCar, Long> {
}
