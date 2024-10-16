package com.nznext.geargrove.products.enums.houses;

import lombok.Getter;

@Getter
public enum FormFactors {
    DESKTOP("Desktop"),
    MINITOWER("Mini Tower"),
    MIDITOWER("Midi Tower"),
    FULLTOWER("Full Tower"),
    ULTRATOWER("Ultra Tower"),
    CUBECASE("Cube Case");

    private final String name;

    FormFactors(String name) {
        this.name = name;
    }
}
