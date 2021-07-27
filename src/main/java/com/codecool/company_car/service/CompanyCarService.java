package com.codecool.company_car.service;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;

import java.util.List;

public interface CompanyCarService {

    List<CompanyCar> findAll();

    CompanyCar findById(Long id);

    CompanyCarCommand saveCompanyCarCommand(CompanyCarCommand command);

    void deleteById(Long id);

    CompanyCar findByDriver(String name);

    List<CompanyCar> findAllByManufacturer(String name);

    List<CompanyCar> findAllByColor(String name);

    List<CompanyCar> findAllNeedsRepair();

    List<CompanyCar> findAllInCity(String name);
}
