package com.codecool.company_car.converter;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityToCityDto implements Converter<City, CityDto> {

    @Synchronized
    @Nullable
    @Override
    public CityDto convert(City source) {
       if (source == null) return null;
       final CityDto cityDto = new CityDto();
       cityDto.setId(source.getId());
       cityDto.setName(source.getName());
       return cityDto;
    }
}
