package com.codecool.company_car.service.implementation;

import com.codecool.company_car.command.DriverCommand;
import com.codecool.company_car.converter.DriverCommandToDriver;
import com.codecool.company_car.converter.DriverToDriverCommand;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.repository.DriverRepository;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverCommandToDriver driverCommandToDriver;
    private final DriverToDriverCommand driverToDriverCommand;

    public DriverServiceImpl(DriverRepository driverRepository, DriverCommandToDriver driverCommandToDriver, DriverToDriverCommand driverToDriverCommand) {
        this.driverRepository = driverRepository;
        this.driverCommandToDriver = driverCommandToDriver;
        this.driverToDriverCommand = driverToDriverCommand;
    }

    @Override
    public Set<Driver> findAll() {
        Set<Driver> drivers = new HashSet<>();
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
    public DriverCommand saveDriverCommand(DriverCommand command) {
        Driver driver = driverCommandToDriver.convert(command);
        Driver savedDriver = driverRepository.save(driver);
        return driverToDriverCommand.convert(savedDriver);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Set<Driver> allDriversFromACity(String name) {
        return findAll().stream().filter(driver -> driver.getCity().getName().equals(name)).collect(Collectors.toSet());
    }
}
