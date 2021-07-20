package com.codecool.company_car.controller;

import com.codecool.company_car.command.DriverCommand;
import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.CityService;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;
    private final CityService cityService;

    public DriverController(DriverService driverService, CityService cityService) {
        this.driverService = driverService;
        this.cityService = cityService;
    }

    @GetMapping
    public Set<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable("id") Long id) {
        return driverService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody DriverCommand command) {
        if (!cityService.findAll().stream().map(City::getName).collect(Collectors.toSet()).contains(command.getCityCommand().getName())) {
            cityService.saveCityCommand(command.getCityCommand());
        }
        driverService.saveDriverCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody DriverCommand command, @PathVariable("id") Long id) {
        command.setDriverId(id);
        driverService.saveDriverCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        driverService.deleteById(id);
    }

    @GetMapping("/{name}")
    public Set<Driver> allFromACity(@PathVariable("name") String name) {
        return driverService.allDriversFromACity(name);
    }
}
