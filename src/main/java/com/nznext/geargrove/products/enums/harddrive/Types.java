package com.nznext.geargrove.products.enums.harddrive;

import lombok.Getter;

@Getter
public enum Types {
    BUILTIN("built_in"),
    EXTERNAL("external");

    private final String name;

    Types(String name) {
        this.name = name;
    }
}
