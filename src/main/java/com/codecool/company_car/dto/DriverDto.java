package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import com.codecool.company_car.annotation.PastMoreThan18Years;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private Long driverId;
    @Name(message = "{driver.firstName}")
    private String firstName;
    @Name(message = "{driver.lastName}")
    private String lastName;
    @Min(value = 1, message = "{driver.cityId}")
    private Long cityId;

    @PastMoreThan18Years(message = "{driver.birthDate}")
    private String birthDate;
}
