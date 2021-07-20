package com.codecool.company_car.converter;

import com.codecool.company_car.command.ColorCommand;
import com.codecool.company_car.model.Color;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ColorCommandToColor implements Converter<ColorCommand, Color> {

    @Synchronized
    @Nullable
    @Override
    public Color convert(ColorCommand source) {
        if (source == null) return null;
        final Color color = new Color();
        color.setId(source.getId());
        color.setName(source.getName());
        return color;
    }
}
