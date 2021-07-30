package com.codecool.company_car.service.implementation;

import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.converter.CityDtoToCity;
import com.codecool.company_car.converter.CityToCityDto;
import com.codecool.company_car.model.City;
import com.codecool.company_car.repository.CityRepository;
import com.codecool.company_car.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityDtoToCity cityDtoToCity;
    private final CityToCityDto cityToCityDto;

    public CityServiceImpl(CityRepository cityRepository, CityDtoToCity cityDtoToCity, CityToCityDto cityToCityDto) {
        this.cityRepository = cityRepository;
        this.cityDtoToCity = cityDtoToCity;
        this.cityToCityDto = cityToCityDto;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
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
    public CityDto saveCityCommand(CityDto command) {
        City city = cityDtoToCity.convert(command);
        City savedCity = cityRepository.save(city);
        return cityToCityDto.convert(savedCity);
    }

    @Override
    public void deleteById(Long id) {
        if (cityRepository.findById(id).get().getDrivers().size() > 0) {
            throw new RuntimeException("There is at least one driver in the city");
        }
        cityRepository.deleteById(id);
    }
}
