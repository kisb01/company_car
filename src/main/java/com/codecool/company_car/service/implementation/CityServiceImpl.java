package com.codecool.company_car.service.implementation;

import com.codecool.company_car.command.CityCommand;
import com.codecool.company_car.converter.CityCommandToCity;
import com.codecool.company_car.converter.CityToCityCommand;
import com.codecool.company_car.model.City;
import com.codecool.company_car.repository.CityRepository;
import com.codecool.company_car.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityCommandToCity cityCommandToCity;
    private final CityToCityCommand cityToCityCommand;

    public CityServiceImpl(CityRepository cityRepository, CityCommandToCity cityCommandToCity, CityToCityCommand cityToCityCommand) {
        this.cityRepository = cityRepository;
        this.cityCommandToCity = cityCommandToCity;
        this.cityToCityCommand = cityToCityCommand;
    }

    @Override
    public Set<City> findAll() {
        Set<City> cities = new HashSet<>();
        cityRepository.findAll().iterator().forEachRemaining(cities::add);
        return cities;
    }

    @Override
    public City findById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            throw new RuntimeException("No City found");
        }
        return optionalCity.get();
    }

    public City findByName(String name) {
        return findAll().stream().filter(c -> c.getName().equals(name)).findFirst().get();
    }

    @Override
    public CityCommand saveCityCommand(CityCommand command) {
        City city = cityCommandToCity.convert(command);
        City savedCity = cityRepository.save(city);
        return cityToCityCommand.convert(savedCity);
    }

    @Override
    public void deleteById(Long id) {
        if (cityRepository.findById(id).get().getDrivers().size() > 0) {
            throw new RuntimeException("There is at least one driver in the city");
        }
        cityRepository.deleteById(id);
    }
}
