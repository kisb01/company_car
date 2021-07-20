package com.codecool.company_car.service;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.model.Driver;

import java.util.Set;

public interface CompanyCarService {

    Set<CompanyCar> findAll();

    CompanyCar findById(Long id);

    CompanyCarCommand saveCompanyCarCommand(CompanyCarCommand command);

    void deleteById(Long id);

    CompanyCar findByDriver(String name);

    Set<CompanyCar> findAllByManufacturer(String name);

    Set<CompanyCar> findAllByColor(String name);

    Set<CompanyCar> findAllNeedsRepair();

    Set<CompanyCar> findAllInCity(String name);
}
