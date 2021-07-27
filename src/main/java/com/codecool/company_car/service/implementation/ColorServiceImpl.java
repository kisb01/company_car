package com.codecool.company_car.service.implementation;

import com.codecool.company_car.command.ColorCommand;
import com.codecool.company_car.converter.ColorCommandToColor;
import com.codecool.company_car.converter.ColorToColorCommand;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.repository.ColorRepository;
import com.codecool.company_car.service.ColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorToColorCommand colorToColorCommand;
    private final ColorCommandToColor colorCommandToColor;

    public ColorServiceImpl(ColorRepository colorRepository, ColorToColorCommand colorToColorCommand, ColorCommandToColor colorCommandToColor) {
        this.colorRepository = colorRepository;
        this.colorToColorCommand = colorToColorCommand;
        this.colorCommandToColor = colorCommandToColor;
    }

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        colorRepository.findAll().iterator().forEachRemaining(colors::add);
        return colors;
    }

    @Override
    public Color findById(Long id) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if (optionalColor.isEmpty()) {
            throw new RuntimeException("No Color found");
        }
        return optionalColor.get();
    }

    @Override
    public ColorCommand saveColorCommand(ColorCommand command) {
        Color color = colorCommandToColor.convert(command);
        Color savedColor = colorRepository.save(color);
        return colorToColorCommand.convert(savedColor);
    }

    @Override
    public void deleteById(Long id) {
        if (colorRepository.findById(id).get().getCompanyCars().size() > 0) {
            throw new RuntimeException("There is at least one company car with this color");
        }
        colorRepository.deleteById(id);
    }
}
