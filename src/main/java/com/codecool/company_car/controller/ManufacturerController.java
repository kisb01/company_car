package com.codecool.company_car.controller;

import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.service.ManufacturerService;
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
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturerPage(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("manufacturer", new ManufacturerDto());
        return "manufacturer";
    }

    @PostMapping
    public String saveOrUpdateManufacturer(@ModelAttribute ManufacturerDto dto) {
        manufacturerService.saveManufacturerDto(dto);
        return "redirect:/manufacturer";
    }

    @GetMapping("{id}/update")
    public String updateManufacturer(@PathVariable Long id, Model model) {
        ManufacturerDto manufacturerDto = manufacturerService.findDtoById(id);

        model.addAttribute("manufacturer", manufacturerDto);
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "manufacturer";
    }

    @GetMapping("{id}/delete")
    public String deleteManufacturerById(@PathVariable Long id) {
        manufacturerService.deleteById(id);
        return "redirect:/manufacturer";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Manufacturer> findAll() {
        return manufacturerService.findAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Manufacturer getById(@PathVariable("id") Long id) {
        return manufacturerService.findById(id);
    }

    @PostMapping("/{id}/get")
    @ResponseBody
    public void add(@RequestBody ManufacturerDto command) {
        manufacturerService.saveManufacturerDto(command);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<ManufacturerDto> update(@Valid @RequestBody ManufacturerDto dto,
                                                  @PathVariable("id") Long id,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        dto.setId(id);
        return ResponseEntity.ok(manufacturerService.saveManufacturerDto(dto));
    }

    @DeleteMapping("{id}/delete")
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        manufacturerService.deleteById(id);
    }
}
