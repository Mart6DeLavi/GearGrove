package com.nznext.geargrove.products.enums.harddrive;

import lombok.Getter;

@Getter
public enum FormFactors {
    F25("2.5"),
    F35("3.5");

    private final String value;

    private FormFactors(String value) {
        this.value = value;
    }
}
