package com.codecool.company_car.controller;

import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.service.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public Manufacturer getById(@PathVariable("id") Long id) {
        return manufacturerService.findById(id);
    }

    @PostMapping
    public void add(@Valid @RequestBody ManufacturerDto manufacturerDto) {
        manufacturerService.saveManufacturerDto(manufacturerDto);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ManufacturerDto manufacturerDto,
                       @PathVariable("id") Long id) {
        manufacturerDto.setId(id);
        manufacturerService.saveManufacturerDto(manufacturerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        manufacturerService.deleteById(id);
    }
}
