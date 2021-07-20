package com.codecool.company_car.converter;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerCommandToManufacturer implements Converter<ManufacturerCommand, Manufacturer> {

    @Synchronized
    @Nullable
    @Override
    public Manufacturer convert(ManufacturerCommand source) {
        if (source == null) return null;
        final Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(source.getId());
        manufacturer.setName(source.getName());
        return manufacturer;
    }
}
