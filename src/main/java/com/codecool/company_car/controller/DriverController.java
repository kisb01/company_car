package com.codecool.company_car.controller;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
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
    public void add(@Valid @RequestBody DriverDto driverDto) {
        driverService.saveDriverDto(driverDto);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody DriverDto driverDto, @PathVariable("id") Long id) {
        driverDto.setDriverId(id);
        driverService.saveDriverDto(driverDto);
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
