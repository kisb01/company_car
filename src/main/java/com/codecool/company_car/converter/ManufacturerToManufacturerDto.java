package com.codecool.company_car.converter;

import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.model.Manufacturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerToManufacturerDto implements Converter<Manufacturer, ManufacturerDto> {

    @Synchronized
    @Nullable
    @Override
    public ManufacturerDto convert(Manufacturer source) {
        if (source == null) return null;
        final ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(source.getId());
        manufacturerDto.setName(source.getName());
        return manufacturerDto;
    }
}
