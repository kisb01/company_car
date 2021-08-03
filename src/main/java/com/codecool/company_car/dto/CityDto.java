package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDto {

    private Long id;
    @Name(message = "City name can not be null")
    private String name;

}
