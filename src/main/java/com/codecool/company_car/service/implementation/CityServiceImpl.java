package com.codecool.company_car.service.implementation;

import com.codecool.company_car.converter.CityDtoToCity;
import com.codecool.company_car.converter.CityToCityDto;
import com.codecool.company_car.dto.CityDto;
import com.codecool.company_car.exception.CityNotFoundException;
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
            throw new CityNotFoundException("No City found with id " + id);
        }
        return optionalCity.get();
    }

    @Override
    public CityDto saveCityDto(CityDto cityDto) {
        City city = cityDtoToCity.convert(cityDto);
        City savedCity = cityRepository.save(city);
        return cityToCityDto.convert(savedCity);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
