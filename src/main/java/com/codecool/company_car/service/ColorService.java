package com.codecool.company_car.service;

import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.model.Color;

import java.util.List;

public interface ColorService {

    List<Color> findAll();

    Color findById(Long id);

    ColorDto saveColorCommand(ColorDto command);

    void deleteById(Long id);
}
