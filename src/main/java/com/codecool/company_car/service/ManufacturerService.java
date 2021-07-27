package com.codecool.company_car.service;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Manufacturer findById(Long id);

    ManufacturerCommand saveManufacturerCommand(ManufacturerCommand command);

    void deleteById(Long id);
}
