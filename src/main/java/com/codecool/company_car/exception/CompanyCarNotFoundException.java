package com.codecool.company_car.exception;

public class CompanyCarNotFoundException extends RuntimeException {
    public CompanyCarNotFoundException(String message) {
        super(message);
    }
}
