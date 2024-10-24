package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.ssd.Interface;
import com.nznext.geargrove.products.enums.ssd.MemoryType;
import com.nznext.geargrove.products.enums.ssd.Types;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SSDInformationDto extends ProductDto{
    Types type;
    int storageCapacity;
    Interface anInterface;
    MemoryType memoryType;

    public SSDInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, Types type, int storageCapacity, Interface anInterface, MemoryType memoryType) {
        super(productName, description, price, quantity, supplier, year);
        this.type = type;
        this.storageCapacity = storageCapacity;
        this.anInterface = anInterface;
        this.memoryType = memoryType;
    }
}
