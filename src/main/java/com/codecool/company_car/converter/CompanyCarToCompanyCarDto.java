package com.codecool.company_car.converter;

import com.codecool.company_car.dto.CompanyCarDto;
import com.codecool.company_car.model.CompanyCar;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CompanyCarToCompanyCarDto implements Converter<CompanyCar, CompanyCarDto> {

    @Synchronized
    @Nullable
    @Override
    public CompanyCarDto convert(CompanyCar source) {
       if (source == null) return null;
       final CompanyCarDto companyCarDto = new CompanyCarDto();
       companyCarDto.setId(source.getId());
       companyCarDto.setLicencePlateNumber(source.getLicencePlateNumber());

       if (source.getManufacturer() != null) {
           companyCarDto.setManufacturerId(source.getManufacturer().getId());
       }
       companyCarDto.setModel(source.getModel());

       if (source.getColor() != null) {
           companyCarDto.setColorId(source.getColor().getId());
       }

       if (source.getDriver() != null) {
           companyCarDto.setDriverId(source.getDriver().getDriverId());
       }

       companyCarDto.setInUseSince(source.getInUseSince());
       companyCarDto.setRepairRequired(source.getRepairRequired());
       return companyCarDto;
    }
}
