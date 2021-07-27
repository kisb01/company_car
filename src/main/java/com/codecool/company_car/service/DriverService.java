package com.codecool.company_car.service;

import com.codecool.company_car.command.DriverCommand;
import com.codecool.company_car.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> findAll();

    Driver findById(Long id);

    DriverCommand saveDriverCommand(DriverCommand command);

    void deleteById(Long id);

    List<Driver> allDriversFromACity(String name);
}
