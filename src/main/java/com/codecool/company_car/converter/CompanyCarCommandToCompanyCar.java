package com.codecool.company_car.converter;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CompanyCarCommandToCompanyCar implements Converter<CompanyCarCommand, CompanyCar> {

    private final ManufacturerCommandToManufacturer manufacturerCommandToManufacturer;
    private final ColorCommandToColor colorCommandToColor;
    private final DriverCommandToDriver driverCommandToDriver;

    public CompanyCarCommandToCompanyCar(ManufacturerCommandToManufacturer manufacturerCommandToManufacturer, ColorCommandToColor colorCommandToColor, DriverCommandToDriver driverCommandToDriver) {
        this.manufacturerCommandToManufacturer = manufacturerCommandToManufacturer;
        this.colorCommandToColor = colorCommandToColor;
        this.driverCommandToDriver = driverCommandToDriver;
    }

    @Synchronized
    @Nullable
    @Override
    public CompanyCar convert(CompanyCarCommand source) {
       if (source == null) return null;
       final CompanyCar companyCar = new CompanyCar();
       companyCar.setId(source.getId());
       companyCar.setLicencePlateNumber(source.getLicencePlateNumber());
       companyCar.setManufacturer(manufacturerCommandToManufacturer.convert(source.getManufacturerCommand()));
       companyCar.setModel(source.getModel());
       companyCar.setColor(colorCommandToColor.convert(source.getColorCommand()));
       companyCar.setDriver(driverCommandToDriver.convert(source.getDriverCommand()));
       companyCar.setInUseSince(source.getInUseSince());
       companyCar.setRepairRequired(source.getRepairRequired());
       return companyCar;
    }
}
