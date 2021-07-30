package com.codecool.company_car.command;

import com.codecool.company_car.annotation.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerCommand {

    private Long id;
    @Name(message = "Manufacturer name can not be null")
    private String name;
}
