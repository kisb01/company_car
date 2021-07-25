package com.codecool.company_car.model;

import java.time.LocalDate;

public @interface PastMoreThan16Years {
    LocalDate currentDate = LocalDate.now();
    LocalDate currentDateMinus16Years = currentDate.minusYears(16);
}
