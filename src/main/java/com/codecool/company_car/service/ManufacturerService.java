package com.codecool.company_car.service;

import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Manufacturer findById(Long id);

    ManufacturerDto saveManufacturerDto(ManufacturerDto manufacturerDto);

    void deleteById(Long id);
}
