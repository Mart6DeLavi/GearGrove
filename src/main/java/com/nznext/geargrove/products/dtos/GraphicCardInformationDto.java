package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.graphcards.BusBitDepth;
import com.nznext.geargrove.products.enums.graphcards.ConnectionInterface;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GraphicCardInformationDto extends ProductDto{
    int memory;
    String memoryType;
    int takeUpSlots;
    ConnectionInterface connectionInterface;
    int numberOfFans;
    int numberOfMonitors;
    int length;
    int additionalPower;
    BusBitDepth busBitDepth;

    public GraphicCardInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, int memory, String memoryType, int takeUpSlots, ConnectionInterface connectionInterface, int numberOfFans, int numberOfMonitors, int length, int additionalPower, BusBitDepth busBitDepth) {
        super(productName, description, price, quantity, supplier, year);
        this.memory = memory;
        this.memoryType = memoryType;
        this.takeUpSlots = takeUpSlots;
        this.connectionInterface = connectionInterface;
        this.numberOfFans = numberOfFans;
        this.numberOfMonitors = numberOfMonitors;
        this.length = length;
        this.additionalPower = additionalPower;
        this.busBitDepth = busBitDepth;
    }
}
