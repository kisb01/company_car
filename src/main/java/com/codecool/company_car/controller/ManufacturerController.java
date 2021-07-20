package com.codecool.company_car.controller;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.service.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public Set<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public Manufacturer getById(@PathVariable("id") Long id) {
        return manufacturerService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody ManufacturerCommand command) {
        manufacturerService.saveManufacturerCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ManufacturerCommand command,
                       @PathVariable("id") Long id) {
        command.setId(id);
        manufacturerService.saveManufacturerCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        manufacturerService.deleteById(id);
    }
}
