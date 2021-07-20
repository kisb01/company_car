package com.codecool.company_car.service.implementation;

import com.codecool.company_car.command.ManufacturerCommand;
import com.codecool.company_car.converter.ManufacturerCommandToManufacturer;
import com.codecool.company_car.converter.ManufacturerToManufacturerCommand;
import com.codecool.company_car.model.Manufacturer;
import com.codecool.company_car.repository.ManufacturerRepository;
import com.codecool.company_car.service.ManufacturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerToManufacturerCommand manufacturerToManufacturerCommand;
    private final ManufacturerCommandToManufacturer manufacturerCommandToManufacturer;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerToManufacturerCommand manufacturerToManufacturerCommand, ManufacturerCommandToManufacturer manufacturerCommandToManufacturer) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerToManufacturerCommand = manufacturerToManufacturerCommand;
        this.manufacturerCommandToManufacturer = manufacturerCommandToManufacturer;
    }

    @Override
    public Set<Manufacturer> findAll() {
        Set<Manufacturer> manufacturers = new HashSet<>();
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
    public ManufacturerCommand saveManufacturerCommand(ManufacturerCommand command) {
        Manufacturer manufacturer = manufacturerCommandToManufacturer.convert(command);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerToManufacturerCommand.convert(savedManufacturer);
    }

    @Override
    public void deleteById(Long id) {
        if (manufacturerRepository.findById(id).get().getCompanyCars().size() > 0) {
            throw new RuntimeException("There is at least one company car from this manufacturer");
        }
        manufacturerRepository.deleteById(id);
    }
}
