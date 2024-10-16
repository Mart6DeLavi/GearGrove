package com.nznext.geargrove.products.enums.houses;

import lombok.Getter;

@Getter
public enum HousingPlacement {
    VERTICAL("Vertical"),
    HORIZONTAL("Horizontal"),
    UNIVERSAL("Universal");

    private final String name;

    HousingPlacement(String name) {
        this.name = name;
    }
}
