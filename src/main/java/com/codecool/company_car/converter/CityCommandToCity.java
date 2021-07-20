package com.codecool.company_car.converter;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityCommandToCity implements Converter<CityCommand, City> {

    @Synchronized
    @Nullable
    @Override
    public City convert(CityCommand source) {
        if (source == null) return null;
        final City city = new City();
        city.setId(source.getId());
        city.setName(source.getName());
        return city;
    }
}
