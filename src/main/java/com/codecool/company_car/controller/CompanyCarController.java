package com.codecool.company_car.controller;

import com.codecool.company_car.converter.StringToLong;
import com.codecool.company_car.dto.CompanyCarDto;
import com.codecool.company_car.exception.CompanyCarNotFoundException;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.service.CompanyCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/companycar")
public class CompanyCarController {

    private final CompanyCarService companyCarService;

    public CompanyCarController(CompanyCarService companyCarService) {
        this.companyCarService = companyCarService;
    }

    @GetMapping
    public List<CompanyCar> findAll() {
        return companyCarService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyCar findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CompanyCarNotFoundException("Must enter a valid number");
        }
        return companyCarService.findById(StringToLong.convert(id));
    }

    @PostMapping
    public void add(@Valid @RequestBody CompanyCarDto companyCarDto) {
        companyCarService.saveCompanyCarDto(companyCarDto);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody CompanyCarDto companyCarDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CompanyCarNotFoundException("Must enter a valid number");
        }
        companyCarDto.setId(StringToLong.convert(id));
        companyCarService.saveCompanyCarDto(companyCarDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CompanyCarNotFoundException("Must enter a valid number");
        }
        companyCarService.deleteById(StringToLong.convert(id));
    }

    @GetMapping("/driver")
    public List<CompanyCar> findByDriver(@RequestParam String name) {
        return companyCarService.findByDriver(name);
    }

    @GetMapping("/manufacturer")
    public List<CompanyCar> findAllByManufacturer(@RequestParam String name) {
        return companyCarService.findAllByManufacturer(name);
    }

    @GetMapping("/color")
    public List<CompanyCar> findAllByColor(@RequestParam String name) {
        return companyCarService.findAllByColor(name);
    }

    @GetMapping("/repair")
    public List<CompanyCar> findAllNeedsRepair() {
        return companyCarService.findAllNeedsRepair();
    }

    @GetMapping("/city")
    public List<CompanyCar> findAllInCity(@RequestParam String name) {
        return companyCarService.findAllInCity(name);
    }

}
