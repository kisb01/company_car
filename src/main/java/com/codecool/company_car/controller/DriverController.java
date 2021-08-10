package com.codecool.company_car.controller;

import com.codecool.company_car.converter.StringToLong;
import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.exception.DriverNotFoundException;
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
    public Driver findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new DriverNotFoundException("Must enter a valid number");
        }
        return driverService.findById(StringToLong.convert(id));
    }

    @PostMapping
    public DriverDto add(@Valid @RequestBody DriverDto driverDto) {
        driverDto.setDriverId(null);
        return driverService.saveDriverDto(driverDto);
    }

    @PutMapping("/{id}")
    public DriverDto update(@Valid @RequestBody DriverDto driverDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new DriverNotFoundException("Must enter a valid number");
        }
        driverDto.setDriverId(StringToLong.convert(id));
        return driverService.saveDriverDto(driverDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new DriverNotFoundException("Must enter a valid number");
        }
        driverService.deleteById(StringToLong.convert(id));
    }

    @GetMapping("/name")
    public List<Driver> allFromACity(@RequestParam String city) {
        return driverService.allDriversFromACity(city);
    }
}
