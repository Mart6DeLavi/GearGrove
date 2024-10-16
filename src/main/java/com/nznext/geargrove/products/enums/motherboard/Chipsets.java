package com.nznext.geargrove.products.enums.motherboard;

import lombok.Getter;

@Getter
public enum Chipsets {
    INTELH510("Intel H510"),
    INTELB560("Interl B560"),
    INTELZ590("Intel Z590"),
    INTELH610("Intel H610"),
    INTELB660("Intel B660"),
    INTELH670("Intel H670"),
    INTELZ690("Intel Z690"),
    INTELB760("Intel B760"),
    INTELZ790("Intel Z790"),
    INTELZ890("Intel Z890"),

    AMDB450("AMD B450"),
    AMDA520("AMD A520"),
    AMDB550("AMD B550"),
    AMDX570("AMD X570"),
    AMDA620("AMD A620"),
    AMDB650("AMD B650"),
    AMDB650E("AMD B650E"),
    AMDX670("AMD X670"),
    AMDX670E("AMD X670E"),
    AMDX870("AMD X870"),
    AMDX870E("AMD X870E");

    private final String name;

    Chipsets(String name) {
        this.name = name;
    }
}
