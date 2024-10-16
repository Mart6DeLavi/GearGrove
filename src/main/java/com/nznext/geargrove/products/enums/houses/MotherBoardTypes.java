package com.nznext.geargrove.products.enums.houses;

import lombok.Getter;

@Getter
public enum MotherBoardTypes {
    MINIITX("mini_ITX"),
    MICROATX("micro_ATX"),
    ATX("ATX"),
    EATX("E-ATX");

    private final String name;

    MotherBoardTypes(String name) {
        this.name = name;
    }
}
