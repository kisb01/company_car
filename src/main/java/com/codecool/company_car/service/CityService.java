package com.codecool.company_car.service;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findById(Long id);

    CityCommand saveCityCommand(CityCommand command);

    void deleteById(Long id);

    City findByName(String name);
}
