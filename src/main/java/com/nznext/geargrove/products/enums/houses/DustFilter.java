package com.nznext.geargrove.products.enums.houses;

import lombok.Getter;

@Getter
public enum DustFilter {
    TOP("Top"),
    DOWN("Down"),
    ONSIDE("On side"),
    INFRONT("In front");

    private final String name;

    DustFilter(String name) {
        this.name = name;
    }
}
