package com.nznext.geargrove.products.enums.powerunit;

import lombok.Getter;

@Getter
public enum FanDiameter {
    D80(80),
    D120(120),
    D135(135),
    D140(140);

    private final int value;

    private FanDiameter(int value) {
        this.value = value;
    }
}
