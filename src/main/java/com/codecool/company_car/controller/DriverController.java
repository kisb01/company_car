package com.codecool.company_car.controller;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.CityService;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable("id") Long id) {
        return driverService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody DriverDto command) {
        driverService.saveDriverCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody DriverDto command, @PathVariable("id") Long id) {
        command.setDriverId(id);
        driverService.saveDriverCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        driverService.deleteById(id);
    }

    @GetMapping("/name")
    public List<Driver> allFromACity(@RequestParam String city) {
        return driverService.allDriversFromACity(city);
    }
}
