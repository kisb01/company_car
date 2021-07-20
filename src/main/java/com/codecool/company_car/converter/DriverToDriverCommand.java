package com.codecool.company_car.converter;

import com.codecool.company_car.command.DriverCommand;
import com.codecool.company_car.model.Driver;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DriverToDriverCommand implements Converter<Driver, DriverCommand> {

    private final CityToCityCommand cityToCityCommand;

    public DriverToDriverCommand(CityToCityCommand cityToCityCommand) {
        this.cityToCityCommand = cityToCityCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public DriverCommand convert(Driver source) {
        if (source == null) return null;
        final DriverCommand driverCommand = new DriverCommand();
        driverCommand.setDriverId(source.getDriverId());
        driverCommand.setFirstName(source.getFirstName());
        driverCommand.setLastName(source.getLastName());
        driverCommand.setCityCommand(cityToCityCommand.convert(source.getCity()));
        driverCommand.setBirthDate(source.getBirthDate());
        return driverCommand;
    }
}
