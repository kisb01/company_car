package com.codecool.company_car.service.implementation;

import com.codecool.company_car.dto.ManufacturerDto;
import com.codecool.company_car.converter.ManufacturerDtoToManufacturer;
import com.codecool.company_car.converter.ManufacturerToManufacturerDto;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.repository.ManufacturerRepository;
import com.codecool.company_car.service.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerToManufacturerDto manufacturerToManufacturerDto;
    private final ManufacturerDtoToManufacturer manufacturerDtoToManufacturer;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerToManufacturerDto manufacturerToManufacturerDto, ManufacturerDtoToManufacturer manufacturerDtoToManufacturer) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerToManufacturerDto = manufacturerToManufacturerDto;
        this.manufacturerDtoToManufacturer = manufacturerDtoToManufacturer;
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturerRepository.findAll().iterator().forEachRemaining(manufacturers::add);
        return manufacturers;
    }

    @Override
    public Manufacturer findById(Long id) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);
        if (optionalManufacturer.isEmpty()) {
            throw new RuntimeException("No Manufacturer found");
        }
        return optionalManufacturer.get();
    }

    @Override
    public ManufacturerDto saveManufacturerCommand(ManufacturerDto command) {
        Manufacturer manufacturer = manufacturerDtoToManufacturer.convert(command);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerToManufacturerDto.convert(savedManufacturer);
    }

    @Override
    public void deleteById(Long id) {
        if (manufacturerRepository.findById(id).get().getCompanyCars().size() > 0) {
            throw new RuntimeException("There is at least one company car from this manufacturer");
        }
        manufacturerRepository.deleteById(id);
    }
}
