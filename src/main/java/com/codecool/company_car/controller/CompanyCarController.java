package com.codecool.company_car.controller;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.service.CompanyCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/companycar")
public class CompanyCarController {

    private final CompanyCarService companyCarService;

    public CompanyCarController(CompanyCarService companyCarService) {
        this.companyCarService = companyCarService;
    }

    @GetMapping
    public Set<CompanyCar> findAll() {
        return companyCarService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyCar findById(@PathVariable("id") Long id) {
        return companyCarService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody CompanyCarCommand command) {
        companyCarService.saveCompanyCarCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CompanyCarCommand command, @PathVariable("id") Long id) {
        command.setId(id);
        companyCarService.saveCompanyCarCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        companyCarService.deleteById(id);
    }

    @GetMapping("/driver")
    public CompanyCar findByDriver(@RequestParam String name) {
        return companyCarService.findByDriver(name);
    }

    @GetMapping("/manufacturer")
    public Set<CompanyCar> findAllByManufacturer(@RequestParam String name) {
        return companyCarService.findAllByManufacturer(name);
    }

    @GetMapping("/color")
    public Set<CompanyCar> findAllByColor(@RequestParam String name) {
        return companyCarService.findAllByColor(name);
    }

    @GetMapping("/repair")
    public Set<CompanyCar> findAllNeedsRepair() {
        return companyCarService.findAllNeedsRepair();
    }

    @GetMapping("/city")
    public Set<CompanyCar> findAllInCity(@RequestParam String name) {
        return companyCarService.findAllInCity(name);
    }

}
