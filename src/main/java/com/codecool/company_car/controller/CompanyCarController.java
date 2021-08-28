package com.codecool.company_car.controller;

import com.codecool.company_car.dto.CompanyCarDto;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/companycars")
public class CompanyCarController {

    private final CompanyCarService companyCarService;
    private final ColorService colorService;
    private final ManufacturerService manufacturerService;
    private final DriverService driverService;
    private final CityService cityService;

    public CompanyCarController(CompanyCarService companyCarService, ColorService colorService, ManufacturerService manufacturerService, DriverService driverService, CityService cityService) {
        this.companyCarService = companyCarService;
        this.colorService = colorService;
        this.manufacturerService = manufacturerService;
        this.driverService = driverService;
        this.cityService = cityService;
    }

    @GetMapping
    public String getCompanyCarPage(Model model) {
        model.addAttribute("companyCars", companyCarService.findAll());
        model.addAttribute("companyCar", new CompanyCarDto());
        model.addAttribute("colors", colorService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("drivers", driverService.findAllAvailable());
        model.addAttribute("cities", cityService.findAll());
        return "companycars";
    }

    @PostMapping
    public String saveOrUpdateCompanyCar(@ModelAttribute CompanyCarDto companyCarDto) {
        companyCarService.saveCompanyCarDto(companyCarDto);
        return "redirect:/companycars";
    }

    @GetMapping("{id}/update")
    public String updateCompanyCar(@PathVariable Long id, Model model) {
        CompanyCarDto companyCarDto = companyCarService.findDtoById(id);

        model.addAttribute("companyCar", companyCarDto);
        model.addAttribute("companyCars", companyCarService.findAll());
        model.addAttribute("colors", colorService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("drivers", driverService.findAllAvailable());
        model.addAttribute("cities", cityService.findAll());
        return "companycars";
    }

    @GetMapping("{id}/delete")
    public String deleteCompanyCarById(@PathVariable Long id) {
        companyCarService.deleteById(id);
        return "redirect:/companycars";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<CompanyCar> findAll() {
        return companyCarService.findAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public CompanyCar findById(@PathVariable("id") Long id) {
        return companyCarService.findById(id);
    }

    @PostMapping("/{id}/get")
    @ResponseBody
    public ResponseEntity<CompanyCarDto> add(@RequestBody CompanyCarDto companyCarDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(companyCarService.saveCompanyCarDto(companyCarDto));
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<CompanyCarDto> updateCompanyCar(@PathVariable("id") Long id, @RequestBody CompanyCarDto companyCarDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        companyCarDto.setId(id);
        return ResponseEntity.ok(companyCarService.saveCompanyCarDto(companyCarDto));
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void deleteById(@PathVariable("id") Long id) {
        companyCarService.deleteById(id);
    }

    @GetMapping("/driver")
    public List<CompanyCar> findByDriver(@RequestParam String name) {
        return companyCarService.findByDriver(name);
    }

    @GetMapping("/manufacturer")
    public List<CompanyCar> findAllByManufacturer(@RequestParam String name) {
        return companyCarService.findAllByManufacturer(name);
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
