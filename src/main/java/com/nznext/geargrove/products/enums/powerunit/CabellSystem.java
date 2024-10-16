package com.nznext.geargrove.products.enums.powerunit;


import lombok.Getter;

@Getter
public enum CabellSystem {

    NOMODULE("No Module"),
    SEMIMODEL("Semi Module"),
    MODULE("Module");

    private final String name;

    private CabellSystem(String name) {
        this.name = name;
    }
}
