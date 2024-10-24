package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.RAM.CASLatency;
import com.nznext.geargrove.products.enums.RAM.OperatingVoltage;
import com.nznext.geargrove.products.enums.RAM.RamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RamInformationDto extends ProductDto{
    int memoryCapacity;
    RamType ramType;
    int clockSpeed;
    CASLatency casLatency;
    OperatingVoltage operatingVoltage;

    public RamInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, int memoryCapacity, RamType ramType, int clockSpeed, CASLatency casLatency, OperatingVoltage operatingVoltage) {
        super(productName, description, price, quantity, supplier, year);
        this.memoryCapacity = memoryCapacity;
        this.ramType = ramType;
        this.clockSpeed = clockSpeed;
        this.casLatency = casLatency;
        this.operatingVoltage = operatingVoltage;
    }
}
