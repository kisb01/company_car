package com.codecool.company_car.converter;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityToCityCommand implements Converter<City, CityCommand> {

    @Synchronized
    @Nullable
    @Override
    public CityCommand convert(City source) {
       if (source == null) return null;
       final CityCommand cityCommand = new CityCommand();
       cityCommand.setId(source.getId());
       cityCommand.setName(source.getName());
       return cityCommand;
    }
}
