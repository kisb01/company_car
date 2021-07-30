package com.codecool.company_car.service.implementation;

import com.codecool.company_car.dto.CompanyCarDto;
import com.codecool.company_car.converter.CompanyCarDtoToCompanyCar;
import com.codecool.company_car.converter.CompanyCarToCompanyCarDto;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.repository.CompanyCarRepository;
import com.codecool.company_car.service.CompanyCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompanyCarServiceImpl implements CompanyCarService {

    private final CompanyCarRepository companyCarRepository;
    private final CompanyCarDtoToCompanyCar companyCarDtoToCompanyCar;
    private final CompanyCarToCompanyCarDto companyCarToCompanyCarDto;

    public CompanyCarServiceImpl(CompanyCarRepository companyCarRepository, CompanyCarDtoToCompanyCar companyCarDtoToCompanyCar, CompanyCarToCompanyCarDto companyCarToCompanyCarDto) {
        this.companyCarRepository = companyCarRepository;
        this.companyCarDtoToCompanyCar = companyCarDtoToCompanyCar;
        this.companyCarToCompanyCarDto = companyCarToCompanyCarDto;
    }

    @Override
    public List<CompanyCar> findAll() {
        List<CompanyCar> companyCars = new ArrayList<>();
        companyCarRepository.findAll().iterator().forEachRemaining(companyCars::add);
        return companyCars;
    }

    @Override
    public CompanyCar findById(Long id) {
        Optional<CompanyCar> optionalCompanyCar = companyCarRepository.findById(id);
        if (optionalCompanyCar.isEmpty()) {
            throw new RuntimeException("No Car found");
        }
        return optionalCompanyCar.get();
    }

    @Override
    public CompanyCarDto saveCompanyCarCommand(CompanyCarDto command) {
        CompanyCar companyCar = companyCarDtoToCompanyCar.convert(command);
        CompanyCar savedCompanyCar = companyCarRepository.save(companyCar);
        return companyCarToCompanyCarDto.convert(savedCompanyCar);
    }

    @Override
    public void deleteById(Long id) {
        companyCarRepository.deleteById(id);
    }

    @Override
    public List<CompanyCar> findByDriver(String name) {
        return companyCarRepository.findByDriverName(name);
    }

    @Override
    public List<CompanyCar> findAllByManufacturer(String name) {
        return findAll().stream().filter(car -> car.getManufacturer().getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public List<CompanyCar> findAllByColor(String name) {
        return findAll().stream().filter(car -> car.getColor().getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public List<CompanyCar> findAllNeedsRepair() {
        return findAll().stream().filter(CompanyCar::getRepairRequired).collect(Collectors.toList());
    }

    @Override
    public List<CompanyCar> findAllInCity(String name) {
        return companyCarRepository.findAllInCity(name);
    }
}
