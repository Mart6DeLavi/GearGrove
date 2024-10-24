package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.motherboard.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MotherBoardInformationDto extends ProductDto{
    FormFactors formFactor;
    Sockets socket;
    Chipsets chipset;
    RAMTypes ramType;
    RAMCapacity ramCapacity;
    int frequency;
    int sataPorts;
    int m2Ports;

    public MotherBoardInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, FormFactors formFactor, Sockets socket, Chipsets chipset, RAMTypes ramType, RAMCapacity ramCapacity, int frequency, int sataPorts, int m2Ports) {
        super(productName, description, price, quantity, supplier, year);
        this.formFactor = formFactor;
        this.socket = socket;
        this.chipset = chipset;
        this.ramType = ramType;
        this.ramCapacity = ramCapacity;
        this.frequency = frequency;
        this.sataPorts = sataPorts;
        this.m2Ports = m2Ports;
    }
}
