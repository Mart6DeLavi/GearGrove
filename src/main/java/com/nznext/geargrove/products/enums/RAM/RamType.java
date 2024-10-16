package com.nznext.geargrove.products.enums.RAM;

import lombok.Getter;

@Getter
public enum RamType {
    DDR3("DDR3"),
    DDR3L("DDR3L"),
    DDR4("DDR4"),
    DDR5("DDR5");

    private final String name;

    RamType(final String name) {
        this.name = name;
    }
}
