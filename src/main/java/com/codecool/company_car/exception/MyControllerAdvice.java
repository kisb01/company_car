package com.codecool.company_car.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class MyControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    public ResponseEntity<String> handleCityException(CityNotFoundException cityNotFoundException) {
        logger.error(cityNotFoundException.getMessage());
        return new ResponseEntity<>(cityNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
