package com.codecool.company_car.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PastMoreThan18YearsValidator.class)
public @interface PastMoreThan18Years {

    String message() default "invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
