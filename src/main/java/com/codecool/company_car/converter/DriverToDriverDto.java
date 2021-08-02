package com.codecool.company_car.converter;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.Driver;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DriverToDriverDto implements Converter<Driver, DriverDto> {

    @Synchronized
    @Nullable
    @Override
    public DriverDto convert(Driver source) {
        if (source == null) return null;
        final DriverDto driverDto = new DriverDto();
        driverDto.setDriverId(source.getDriverId());
        driverDto.setFirstName(source.getFirstName());
        driverDto.setLastName(source.getLastName());
        driverDto.setCityId(source.getCity().getId());
        driverDto.setBirthDate(source.getBirthDate().toString());
        return driverDto;
    }
}
