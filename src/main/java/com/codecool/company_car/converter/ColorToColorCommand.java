package com.codecool.company_car.converter;

import com.codecool.company_car.command.ColorCommand;
import com.codecool.company_car.model.Color;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ColorToColorCommand implements Converter<Color, ColorCommand> {

    @Synchronized
    @Nullable
    @Override
    public ColorCommand convert(Color source) {
       if (source == null) return null;
       final ColorCommand colorCommand = new ColorCommand();
       colorCommand.setId(source.getId());
       colorCommand.setName(source.getName());
       return colorCommand;
    }
}
