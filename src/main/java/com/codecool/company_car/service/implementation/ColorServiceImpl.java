package com.codecool.company_car.service.implementation;

import com.codecool.company_car.converter.ColorDtoToColor;
import com.codecool.company_car.converter.ColorToColorDto;
import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.exception.ColorNotFoundException;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.repository.ColorRepository;
import com.codecool.company_car.service.ColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorToColorDto colorToColorDto;
    private final ColorDtoToColor colorDtoToColor;

    public ColorServiceImpl(ColorRepository colorRepository, ColorToColorDto colorToColorDto, ColorDtoToColor colorDtoToColor) {
        this.colorRepository = colorRepository;
        this.colorToColorDto = colorToColorDto;
        this.colorDtoToColor = colorDtoToColor;
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
            throw new ColorNotFoundException("No Color found with id " + id);
        }
        return optionalColor.get();
    }

    @Override
    public ColorDto saveColorCommand(ColorDto command) {
        Color color = colorDtoToColor.convert(command);
        Color savedColor = colorRepository.save(color);
        return colorToColorDto.convert(savedColor);
    }

    @Override
    public void deleteById(Long id) {
        if (colorRepository.findById(id).get().getCompanyCars().size() > 0) {
            throw new RuntimeException("There is at least one company car with this color");
        }
        colorRepository.deleteById(id);
    }
}
