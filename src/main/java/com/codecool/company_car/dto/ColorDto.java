package com.codecool.company_car.dto;

import com.codecool.company_car.annotation.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColorDto {

    private Long id;
    @Name(message = "{color.name}")
    private String name;
}
