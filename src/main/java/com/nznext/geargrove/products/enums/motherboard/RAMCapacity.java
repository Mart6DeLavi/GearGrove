package com.nznext.geargrove.products.enums.motherboard;

import lombok.Getter;

@Getter
public enum RAMCapacity {
    R32(32),
    R64(64),
    R96(96),
    R128(128),
    R192(192);

    private final int value;

    RAMCapacity(final int value) {
        this.value = value;
    }
}
