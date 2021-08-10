package com.codecool.company_car.controller;

import com.codecool.company_car.converter.StringToLong;
import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.exception.ManufacturerNotFoundException;
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
    public Manufacturer findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new ManufacturerNotFoundException("Must enter a valid number");
        }
        return manufacturerService.findById(StringToLong.convert(id));
    }

    @PostMapping
    public ManufacturerDto add(@Valid @RequestBody ManufacturerDto manufacturerDto) {
        manufacturerDto.setId(null);
        return manufacturerService.saveManufacturerDto(manufacturerDto);
    }

    @PutMapping("/{id}")
    public ManufacturerDto update(@Valid @RequestBody ManufacturerDto manufacturerDto,
                                  @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new ManufacturerNotFoundException("Must enter a valid number");
        }
        manufacturerDto.setId(StringToLong.convert(id));
        return manufacturerService.saveManufacturerDto(manufacturerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new ManufacturerNotFoundException("Must enter a valid number");
        }
        manufacturerService.deleteById(StringToLong.convert(id));
    }
}
