package com.nznext.geargrove.products.enums.graphcards;

import lombok.Getter;

@Getter
public enum ConnectionInterface {
    PCI30("PCI v3.0"),
    PCI40("PCI v4.0");

    private final String name;

    ConnectionInterface(String name) {
        this.name = name;
    }
}