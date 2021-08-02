package com.codecool.company_car.repository;

import com.codecool.company_car.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM driver WHERE driver_id NOT IN (SELECT driver_driver_id FROM company_car WHERE driver_driver_id IS NOT NULL)", nativeQuery = true)
    List<Driver> findAllAvailable();
}
