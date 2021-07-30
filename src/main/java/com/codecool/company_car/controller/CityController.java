package com.codecool.company_car.controller;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public City findById(@PathVariable("id") @NotNull Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CityDto> add(@Valid @RequestBody CityDto command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
       return ResponseEntity.ok(cityService.saveCityCommand(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@Valid @RequestBody CityDto command, @PathVariable("id") Long id, BindingResult bindingResult) {
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
