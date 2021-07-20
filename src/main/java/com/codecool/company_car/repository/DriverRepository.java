package com.codecool.company_car.repository;

import com.codecool.company_car.model.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Long> {
}
