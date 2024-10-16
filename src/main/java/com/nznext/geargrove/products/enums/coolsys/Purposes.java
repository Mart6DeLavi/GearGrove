package com.nznext.geargrove.products.enums.coolsys;

import lombok.Getter;

@Getter
public enum Purposes {
    PROCESSOR("processor"),
    HOUSING("housing");

    private final String name;

    private Purposes(String name) {
        this.name = name;
    }
}
