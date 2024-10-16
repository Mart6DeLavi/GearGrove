package com.nznext.geargrove.products.enums.harddrive;

import lombok.Getter;

@Getter
public enum ClipBoardSize {
    MB8(8),
    MB16(16),
    MB32(32),
    MB64(64),
    MB128(128),
    MB256(256),
    MB512(512);

    private final int size;

    ClipBoardSize(int size) {
        this.size = size;
    }
}
