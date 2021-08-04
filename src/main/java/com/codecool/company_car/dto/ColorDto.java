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
    @Name(message = "Color name should be at least 2 letters with a capital first letter")
    private String name;
}
