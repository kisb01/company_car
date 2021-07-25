package com.codecool.company_car.converter;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.CompanyCar;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CompanyCarToCompanyCarCommand implements Converter<CompanyCar, CompanyCarCommand> {

    @Synchronized
    @Nullable
    @Override
    public CompanyCarCommand convert(CompanyCar source) {
       if (source == null) return null;
       final CompanyCarCommand companyCarCommand = new CompanyCarCommand();
       companyCarCommand.setId(source.getId());
       companyCarCommand.setLicencePlateNumber(source.getLicencePlateNumber());

       if (source.getManufacturer() != null) {
           companyCarCommand.setManufacturerId(source.getManufacturer().getId());
       }
       companyCarCommand.setModel(source.getModel());

       if (source.getColor() != null) {
           companyCarCommand.setColorId(source.getColor().getId());
       }

       if (source.getDriver() != null) {
           companyCarCommand.setDriverId(source.getDriver().getDriverId());
       }

       companyCarCommand.setInUseSince(source.getInUseSince());
       companyCarCommand.setRepairRequired(source.getRepairRequired());
       return companyCarCommand;
    }
}
