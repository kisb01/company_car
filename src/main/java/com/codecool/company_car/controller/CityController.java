package com.codecool.company_car.controller;

import com.codecool.company_car.converter.StringToLong;
import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.exception.CityNotFoundException;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public void add(@Valid @RequestBody CityDto cityDto) {
        cityService.saveCityDto(cityDto);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody CityDto cityDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number");
        }
        cityDto.setId(StringToLong.convert(id));
        cityService.saveCityDto(cityDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number");
        }
        cityService.deleteById(StringToLong.convert(id));
    }
}
