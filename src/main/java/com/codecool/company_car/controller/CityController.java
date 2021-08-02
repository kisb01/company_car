package com.codecool.company_car.controller;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
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
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String getCityPage(Model model) {
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("city", new CityDto());
        return "city";
    }

    @PostMapping
    public String saveOrUpdateCity(@ModelAttribute CityDto cityDto) {
        cityService.saveCityDto(cityDto);
        return "redirect:/city";
    }

    @GetMapping("{id}/update")
    public String updateCity(@PathVariable Long id, Model model) {
        CityDto cityDto = cityService.findDtoById(id);

        model.addAttribute("city", cityDto);
        model.addAttribute("cities", cityService.findAll());
        return "city";
    }

    @GetMapping("{id}/delete")
    public String deleteCityById(@PathVariable Long id) {
        cityService.deleteById(id);
        return "redirect:/city";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping("/{id}/get")
    @ResponseBody
    public ResponseEntity<CityDto> add(@Valid @RequestBody CityDto cityDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
       return ResponseEntity.ok(cityService.saveCityDto(cityDto));
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<CityDto> update(@Valid @RequestBody CityDto cityDto, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        cityDto.setId(id);
        return ResponseEntity.ok(cityService.saveCityDto(cityDto));
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        cityService.deleteById(id);
    }
}
