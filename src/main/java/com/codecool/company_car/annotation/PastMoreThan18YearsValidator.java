package com.codecool.company_car.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PastMoreThan18YearsValidator implements ConstraintValidator<PastMoreThan18Years, LocalDate> {

    @Override
    public void initialize(PastMoreThan18Years constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate != null &&
                localDate.isBefore(LocalDate.now().minusYears(18));
    }
}
