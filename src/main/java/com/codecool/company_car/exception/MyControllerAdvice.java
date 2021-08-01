package com.codecool.company_car.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityException(CityNotFoundException cityNotFoundException) {
        logger.error(cityNotFoundException.getMessage());
        return new ResponseEntity<>(cityNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ColorNotFoundException.class)
    public ResponseEntity<String> handleColorException(ColorNotFoundException colorNotFoundException) {
        logger.error(colorNotFoundException.getMessage());
        return new ResponseEntity<>(colorNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompanyCarNotFoundException.class)
    public ResponseEntity<String> handleCompanyCarException(CompanyCarNotFoundException companyCarNotFoundException) {
        logger.error(companyCarNotFoundException.getMessage());
        return new ResponseEntity<>(companyCarNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDriverException(DriverNotFoundException driverNotFoundException) {
        logger.error(driverNotFoundException.getMessage());
        return new ResponseEntity<>(driverNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManufacturerNotFoundException.class)
    public ResponseEntity<String> handleManufacturerException(ManufacturerNotFoundException manufacturerNotFoundException) {
        logger.error(manufacturerNotFoundException.getMessage());
        return new ResponseEntity<>(manufacturerNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}