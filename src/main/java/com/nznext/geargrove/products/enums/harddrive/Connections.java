package com.nznext.geargrove.products.enums.harddrive;

import lombok.Getter;

@Getter
public enum Connections {
    USB32GEN1("USB 3.2 gen1"),
    USB32GEN2("USB 3.2 gen2"),
    USBC32GEN1("USB C 3.2 gen1"),
    USBC32GEN2("USB C 3.2 gen2"),
    USBCTHUNDERBOLTV3("USB C Thunderbolt v3"),
    SATA3("SATA 3"),
    SAS("SAS");

    private final String name;

    private Connections(String name) {
        this.name = name;
    }
}
