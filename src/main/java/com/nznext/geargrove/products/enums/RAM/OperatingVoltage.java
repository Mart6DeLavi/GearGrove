package com.nznext.geargrove.products.enums.RAM;

import lombok.Getter;

@Getter
public enum OperatingVoltage {
    V11(1.1),
    V12(1.2),
    V125(1.25),
    V135(1.35),
    V14(1.4),
    V145(1.45),
    V15(1.5);

    private final Double name;
    OperatingVoltage(Double name) {
        this.name = name;
    }
}
