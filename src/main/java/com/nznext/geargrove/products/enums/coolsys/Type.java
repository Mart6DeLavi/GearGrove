package com.nznext.geargrove.products.enums.coolsys;

import lombok.Getter;

@Getter
public enum Type {
    FAN("Fan"),
    SIDEFAN("Side Fan"),// боковой кулер
    WATERCOOLING("Water Cooling"),
    M2RADIATOR("M.2 Radiator"),
    WATERPUMPCPU("Waterpump CPU"),
    CPOSET("Set CPO"), //комлпект CPO
    RADIATOR("radiator");

    private final String name;
    Type(String name) {
        this.name = name;
    }
}
