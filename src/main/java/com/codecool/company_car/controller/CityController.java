package com.codecool.company_car.controller;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Set<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody CityCommand command) {
        cityService.saveCityCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CityCommand command, @PathVariable("id") Long id) {
        command.setId(id);
        cityService.saveCityCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cityService.deleteById(id);
    }
}
