package com.nznext.geargrove.products.enums.ssd;

import lombok.Getter;

@Getter
public enum Types {
    BUILTIN("built-in"),
    EXTERNAL("external");

    private final String name;

    Types(String name) {
        this.name = name;
    }
}
