package com.nznext.geargrove.products.enums.coolsys;

import lombok.Getter;

@Getter
public enum FanDiameter {
    MM40(40),
    MM60(60),
    MM80(80),
    MM92(92),
    MM120(120),
    MM135(135),
    MM140(140),
    MM200(200);

    private final int value;

    FanDiameter(int value) {
        this.value = value;
    }
}
