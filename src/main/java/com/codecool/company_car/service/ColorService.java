package com.codecool.company_car.service;

import com.codecool.company_car.command.ColorCommand;
import com.codecool.company_car.model.Color;

import java.util.List;

public interface ColorService {

    List<Color> findAll();

    Color findById(Long id);

    ColorCommand saveColorCommand(ColorCommand command);

    void deleteById(Long id);
}
