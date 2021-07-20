package com.codecool.company_car.service;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;

import java.util.Set;

public interface ManufacturerService {

    Set<Manufacturer> findAll();

    Manufacturer findById(Long id);

    ManufacturerCommand saveManufacturerCommand(ManufacturerCommand command);

    void deleteById(Long id);
}
