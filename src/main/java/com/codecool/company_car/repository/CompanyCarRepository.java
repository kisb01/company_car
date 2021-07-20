package com.codecool.company_car.repository;

import com.codecool.company_car.model.CompanyCar;
import org.springframework.data.repository.CrudRepository;

public interface CompanyCarRepository extends CrudRepository<CompanyCar, Long> {
}
