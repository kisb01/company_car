package com.codecool.company_car.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

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

    @ExceptionHandler(CityHasDriverException.class)
    public ResponseEntity<String> handleCityHasDriverException(CityHasDriverException cityHasDriverException) {
        logger.error(cityHasDriverException.getMessage());
        return new ResponseEntity<>(cityHasDriverException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ColorHasCarException.class)
    public ResponseEntity<String> handleColorHasCarException(ColorHasCarException colorHasCarException) {
        logger.error(colorHasCarException.getMessage());
        return new ResponseEntity<>(colorHasCarException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManufacturerHasCarException.class)
    public ResponseEntity<String> handleManufacturerHasCarException(ManufacturerHasCarException manufacturerHasCarException) {
        logger.error(manufacturerHasCarException.getMessage());
        return new ResponseEntity<>(manufacturerHasCarException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder sb = new StringBuilder("Posted entity contains error(s): " + exception.getErrorCount()).append(System.lineSeparator());
        exception.getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String msg = err.getDefaultMessage();
            sb.append(fieldName).append(" ").append(msg).append(System.lineSeparator());
        });
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
