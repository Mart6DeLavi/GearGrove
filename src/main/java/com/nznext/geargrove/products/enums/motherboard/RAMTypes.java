package com.nznext.geargrove.products.enums.motherboard;

import lombok.Getter;

@Getter
public enum RAMTypes {
    DDR4("DDR4"),
    DDR5("DDR5");

    private final String name;

    RAMTypes(final String name) {
        this.name = name;
    }
}
