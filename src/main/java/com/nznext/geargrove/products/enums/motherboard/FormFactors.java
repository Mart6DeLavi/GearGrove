package com.nznext.geargrove.products.enums.motherboard;

import lombok.Getter;

@Getter
public enum FormFactors {
    MINI_ITX("mini_ITX"),
    MICRO_ATX("micro_ATX"),
    ATX("ATX"),
    E_ATX("E-ATX");

    private final String value;

    FormFactors(String value) {
        this.value = value;
    }
}
