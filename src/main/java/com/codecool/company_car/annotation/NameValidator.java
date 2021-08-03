package com.codecool.company_car.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null &&
                !s.isBlank() &&
                s.length() >= 2 &&
                s.length() <= maxLength &&
                Character.isUpperCase(s.charAt(0));
    }
}
