package com.nznext.geargrove.products.enums.ssd;

import lombok.Getter;

@Getter
public enum Interface {
    I25("2.5"),
    IM2("M.2"),
    MINISATA("Mini-SATA");

    private final String name;

    Interface(String name) {
        this.name = name;
    }
}
