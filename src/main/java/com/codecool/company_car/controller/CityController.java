package com.codecool.company_car.controller;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CityCommand> add(@Valid @RequestBody CityCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
       return ResponseEntity.ok(cityService.saveCityCommand(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityCommand> update(@Valid @RequestBody CityCommand command, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        command.setId(id);
        return ResponseEntity.ok(cityService.saveCityCommand(command));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cityService.deleteById(id);
    }
}
