package com.codecool.company_car.controller;

import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.service.ColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/color")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public String getColorPage(Model model) {
        model.addAttribute("colors", colorService.findAll());
        model.addAttribute("color", new ColorDto());
        return "color";
    }

    @PostMapping
    public String saveOrUpdateColor(@ModelAttribute ColorDto colorDto) {
        colorService.saveColorDto(colorDto);
        return "redirect:/color";
    }

    @GetMapping("{id}/update")
    public String updateColor(@PathVariable Long id, Model model) {
        ColorDto colorDto = colorService.findDtoById(id);

        model.addAttribute("color", colorDto);
        model.addAttribute("colors", colorService.findAll());
        return "color";
    }

    @GetMapping("{id}/delete")
    public String deleteColorById(@PathVariable Long id) {
        colorService.deleteById(id);
        return "redirect:/color";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Color> findAll() {
        return colorService.findAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Color findById(@PathVariable("id") Long id) {
        return colorService.findById(id);
    }

    @PostMapping("/{id}/get")
    @ResponseBody
    public void add(@RequestBody ColorDto command) {
        colorService.saveColorDto(command);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void update(@RequestBody ColorDto command, @PathVariable("id") Long id) {
        command.setId(id);
        colorService.saveColorDto(command);
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        colorService.deleteById(id);
    }
}
