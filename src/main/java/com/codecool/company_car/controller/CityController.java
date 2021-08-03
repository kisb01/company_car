package com.codecool.company_car.controller;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public City findById(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CityDto> add(@Valid @RequestBody CityDto cityDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Posted City entity contains error(s): " + bindingResult.getErrorCount());
            logger.error(cityDto.toString());
            bindingResult.getAllErrors().forEach(err -> {
                String msg = err.getObjectName() + " " + err.getCode() + " " + err.getDefaultMessage();
                logger.error(msg);
            });
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cityService.saveCityDto(cityDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@Valid @RequestBody CityDto cityDto, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Posted City entity contains error(s): " + bindingResult.getErrorCount());
            logger.error(cityDto.toString());
            bindingResult.getAllErrors().forEach(err -> {
                var msg = err.getObjectName() + " " + err.getCode() + " " + err.getDefaultMessage();
                logger.error(msg);
            });
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cityDto.setId(id);
        return ResponseEntity.ok(cityService.saveCityDto(cityDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cityService.deleteById(id);
    }
}
