package com.codecool.company_car.converter;

import com.codecool.company_car.command.CompanyCarCommand;
import com.codecool.company_car.model.Color;
import com.codecool.company_car.model.CompanyCar;
import com.codecool.company_car.model.Driver;
import com.codecool.company_car.model.Manufacturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CompanyCarCommandToCompanyCar implements Converter<CompanyCarCommand, CompanyCar> {

    @Synchronized
    @Nullable
    @Override
    public CompanyCar convert(CompanyCarCommand source) {
        if (source == null) return null;
        final CompanyCar companyCar = new CompanyCar();
        companyCar.setId(source.getId());
        companyCar.setLicencePlateNumber(source.getLicencePlateNumber());

        if (source.getManufacturerId() != null) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId(source.getManufacturerId());
            manufacturer.getCompanyCars().add(companyCar);
            companyCar.setManufacturer(manufacturer);
        }
        companyCar.setModel(source.getModel());

        if (source.getColorId() != null) {
            Color color = new Color();
            color.setId(source.getColorId());
            color.getCompanyCars().add(companyCar);
            companyCar.setColor(color);
        }

        if (source.getDriverId() != null) {
            Driver driver = new Driver();
            driver.setDriverId(source.getDriverId());
            driver.setCompanyCar(companyCar);
            companyCar.setDriver(driver);
        }

        companyCar.setInUseSince(source.getInUseSince());
        companyCar.setRepairRequired(source.getRepairRequired());
        return companyCar;
    }
}
