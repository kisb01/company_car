package com.codecool.company_car.service;

import com.codecool.company_car.command.DriverCommand;
import com.codecool.company_car.model.Driver;

import java.util.Set;

public interface DriverService {

    Set<Driver> findAll();

    Driver findById(Long id);

    DriverCommand saveDriverCommand(DriverCommand command);

    void deleteById(Long id);

    Set<Driver> allDriversFromACity(String name);
}
