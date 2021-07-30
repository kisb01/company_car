package com.codecool.company_car.converter;

import com.codecool.company_car.dto.DriverDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.model.Driver;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DriverDtoToDriver implements Converter<DriverDto, Driver> {

    @Synchronized
    @Nullable
    @Override
    public Driver convert(DriverDto source) {
        if (source == null) return null;
        final Driver driver = new Driver();
        driver.setDriverId(source.getDriverId());
        driver.setFirstName(source.getFirstName());
        driver.setLastName(source.getLastName());

        if (source.getCityId() != null) {
            City city = new City();
            city.setId(source.getCityId());
            city.getDrivers().add(driver);
            driver.setCity(city);
        }
        driver.setBirthDate(source.getBirthDate());
        return driver;
    }
}
