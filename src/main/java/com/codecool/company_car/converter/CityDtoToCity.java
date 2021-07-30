package com.codecool.company_car.converter;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityDtoToCity implements Converter<CityDto, City> {

    @Synchronized
    @Nullable
    @Override
    public City convert(CityDto source) {
        if (source == null) return null;
        final City city = new City();
        city.setId(source.getId());
        city.setName(source.getName());
        return city;
    }
}
