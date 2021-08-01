package com.codecool.company_car.exception;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String message) {
        super(message);
    }
}
