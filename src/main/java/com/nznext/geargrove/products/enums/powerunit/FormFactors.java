package com.nznext.geargrove.products.enums.powerunit;

import lombok.Getter;

@Getter
public enum FormFactors {
    ATX("ATX"),
    TFX("TFX"),
    SFX("SFX");

    private final String name;

    private FormFactors(String name) {
        this.name = name;
    }
}
