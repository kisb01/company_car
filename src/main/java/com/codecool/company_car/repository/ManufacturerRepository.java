package com.codecool.company_car.repository;

import com.codecool.company_car.model.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
}
