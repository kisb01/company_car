package com.codecool.company_car.converter;

import com.codecool.company_car.dto.ColorDto;
import com.codecool.company_car.model.Color;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ColorToColorDto implements Converter<Color, ColorDto> {

    @Synchronized
    @Nullable
    @Override
    public ColorDto convert(Color source) {
       if (source == null) return null;
       final ColorDto colorDto = new ColorDto();
       colorDto.setId(source.getId());
       colorDto.setName(source.getName());
       return colorDto;
    }
}
