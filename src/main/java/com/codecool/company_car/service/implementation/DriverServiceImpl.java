package com.codecool.company_car.service.implementation;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.converter.DriverDtoToDriver;
import com.codecool.company_car.converter.DriverToDriverDto;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.repository.DriverRepository;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverDtoToDriver driverDtoToDriver;
    private final DriverToDriverDto driverToDriverDto;

    public DriverServiceImpl(DriverRepository driverRepository, DriverDtoToDriver driverDtoToDriver, DriverToDriverDto driverToDriverDto) {
        this.driverRepository = driverRepository;
        this.driverDtoToDriver = driverDtoToDriver;
        this.driverToDriverDto = driverToDriverDto;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        driverRepository.findAll().iterator().forEachRemaining(drivers::add);
        return drivers;
    }

    @Override
    public Driver findById(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isEmpty()) {
            throw new RuntimeException("No Driver found");
        }
        return optionalDriver.get();
    }

    @Override
    public DriverDto saveDriverCommand(DriverDto command) {
        Driver driver = driverDtoToDriver.convert(command);
        Driver savedDriver = driverRepository.save(driver);
        return driverToDriverDto.convert(savedDriver);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<Driver> allDriversFromACity(String name) {
        return findAll().stream().filter(driver -> driver.getCity().getName().equals(name)).collect(Collectors.toList());
    }
}
