package com.codecool.company_car.converter;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CompanyCarToCompanyCarCommand implements Converter<CompanyCar, CompanyCarCommand> {

    private final ManufacturerToManufacturerCommand manufacturerToManufacturerCommand;
    private final ColorToColorCommand colorToColorCommand;
    private final DriverToDriverCommand driverToDriverCommand;

    public CompanyCarToCompanyCarCommand(ManufacturerToManufacturerCommand manufacturerToManufacturerCommand, ColorToColorCommand colorToColorCommand, DriverToDriverCommand driverToDriverCommand) {
        this.manufacturerToManufacturerCommand = manufacturerToManufacturerCommand;
        this.colorToColorCommand = colorToColorCommand;
        this.driverToDriverCommand = driverToDriverCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public CompanyCarCommand convert(CompanyCar source) {
       if (source == null) return null;
       final CompanyCarCommand companyCarCommand = new CompanyCarCommand();
       companyCarCommand.setId(source.getId());
       companyCarCommand.setLicencePlateNumber(source.getLicencePlateNumber());
       companyCarCommand.setManufacturerCommand(manufacturerToManufacturerCommand.convert(source.getManufacturer()));
       companyCarCommand.setModel(source.getModel());
       companyCarCommand.setColorCommand(colorToColorCommand.convert(source.getColor()));
       companyCarCommand.setDriverCommand(driverToDriverCommand.convert(source.getDriver()));
       companyCarCommand.setInUseSince(source.getInUseSince());
       companyCarCommand.setRepairRequired(source.getRepairRequired());
       return companyCarCommand;
    }
}
