package com.codecool.company_car.controller;

import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.service.ColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/color")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> findAll() {
        return colorService.findAll();
    }

    @GetMapping("/{id}")
    public Color findById(@PathVariable("id") Long id) {
        return colorService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody ColorDto command) {
        colorService.saveColorCommand(command);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ColorDto command, @PathVariable("id") Long id) {
        command.setId(id);
        colorService.saveColorCommand(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        colorService.deleteById(id);
    }
}
