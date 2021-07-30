package com.codecool.company_car.service;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findById(Long id);

    CityDto saveCityCommand(CityDto command);

    void deleteById(Long id);

    City findByName(String name);
}
