package com.codecool.company_car.controller;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public String getDriverPage(Model model) {
        model.addAttribute("drivers", driverService.findAll());
        model.addAttribute("driver", new DriverDto());
        return "driver";
    }

    @PostMapping
    public String saveOrUpdateDriver(@ModelAttribute DriverDto driverDto) {
        driverService.saveDriverDto(driverDto);
        return "redirect:/driver";
    }

    @GetMapping("{id}/update")
    public String updateDriver(@PathVariable Long id, Model model) {
        DriverDto driverDto = driverService.findDtoById(id);

        model.addAttribute("driver", driverDto);
        model.addAttribute("drivers", driverService.findAll());
        return "driver";
    }

    @GetMapping("{id}/delete")
    public String deleteDriverById(@PathVariable Long id) {
        driverService.deleteById(id);
        return "redirect:/driver";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Driver findById(@PathVariable("id") Long id) {
        return driverService.findById(id);
    }

    @PostMapping("/{id}/get")
    @ResponseBody
    public ResponseEntity<DriverDto> add(@Valid @RequestBody DriverDto driverDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(driverService.saveDriverDto(driverDto));
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<DriverDto> update(@RequestBody DriverDto driverDto, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        driverDto.setDriverId(id);
        return ResponseEntity.ok(driverService.saveDriverDto(driverDto));
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        driverService.deleteById(id);
    }

    @GetMapping("/name")
    public List<Driver> allFromACity(@RequestParam String city) {
        return driverService.allDriversFromACity(city);
    }
}
