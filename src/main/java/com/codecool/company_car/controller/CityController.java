package com.codecool.company_car.controller;

import com.codecool.company_car.converter.StringToLong;
import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.exception.CityNotFoundException;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public City findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number");
        }
        return cityService.findById(StringToLong.convert(id));
    }

    @PostMapping
    public CityDto add(@Valid @RequestBody CityDto cityDto) {
        cityDto.setId(null);
        return cityService.saveCityDto(cityDto);
    }

    @PutMapping("/{id}")
    public CityDto update(@Valid @RequestBody CityDto cityDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number");
        }
        cityDto.setId(StringToLong.convert(id));
        return cityService.saveCityDto(cityDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number");
        }
        cityService.deleteById(StringToLong.convert(id));
    }
}
