package com.nznext.geargrove.products.enums.motherboard;

import lombok.Getter;

@Getter
public enum Sockets {
    AMDAM4("AMD AM4"),
    AMDAM5("AMD AM5"),
    INTEL1200("Intel 1200"),
    INTEL1700("Intel 1700");

    private final String name;

     Sockets(final String name) {
        this.name = name;
    }
}
