package com.nznext.geargrove.products.enums.powerunit;

import lombok.Getter;

@Getter
public enum Certificate {
    BRONZE("BROZNZE"),
    WHITE("WHITE"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    PLATINUM("PLATINUM"),
    TITANIUM("TITANIUM");

    private final String name;

    Certificate(String name) {
        this.name = name;
    }
}
