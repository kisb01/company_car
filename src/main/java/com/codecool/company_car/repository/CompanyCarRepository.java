package com.codecool.company_car.repository;

import com.codecool.company_car.model.CompanyCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CompanyCarRepository extends JpaRepository<CompanyCar, Long> {

    @Query("from CompanyCar c where c.driver.city.name like ?1")
    Set<CompanyCar> findAllInCity(String name);

    @Query("from CompanyCar c where c.driver.firstName like %:name% or c.driver.lastName like %:name%")
    CompanyCar findByDriverName(String name);

}
