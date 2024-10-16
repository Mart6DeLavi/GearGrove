package com.nznext.geargrove.products.enums.ssd;

import lombok.Getter;

@Getter
public enum MemoryType {
    MLC("MLC"),
    TLC("TLC"),
    MLCNAND3D("3D MLC NAND"),
    TLCNAND3D("3D TLC NAND"),
    QLCNAND3D("3D QLC NAND"),
    NAND3D("3D NAND");

    private final String name;

    private MemoryType(String name) {
        this.name = name;
    }
}
