package com.nznext.geargrove.products.enums;

import lombok.Getter;

@Getter
public enum BusBitDepth {
    B128(128),
    B192(192),
    B256(256),
    B352(352),
    B384(384);

    private final int value;
    BusBitDepth(int value) {
        this.value = value;
    }
}
