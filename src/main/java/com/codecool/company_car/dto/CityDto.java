package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    private Long id;
    @Name(message = "{city.name}")
    private String name;

}
