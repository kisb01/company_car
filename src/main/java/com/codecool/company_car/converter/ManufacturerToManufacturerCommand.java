package com.codecool.company_car.converter;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerToManufacturerCommand implements Converter<Manufacturer, ManufacturerCommand> {

    @Synchronized
    @Nullable
    @Override
    public ManufacturerCommand convert(Manufacturer source) {
        if (source == null) return null;
        final ManufacturerCommand manufacturerCommand = new ManufacturerCommand();
        manufacturerCommand.setId(source.getId());
        manufacturerCommand.setName(source.getName());
        return manufacturerCommand;
    }
}
