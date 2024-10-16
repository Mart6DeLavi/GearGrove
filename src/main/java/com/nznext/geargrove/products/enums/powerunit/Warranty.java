package com.nznext.geargrove.products.enums.powerunit;

import lombok.Getter;

@Getter
public enum Warranty {
    Y2(2),
    Y3(3),
    Y5(5),
    Y7(7),
    Y10(10),
    Y12(12);

    private final int value;

    private Warranty(int value) {
        this.value = value;
    }
}
