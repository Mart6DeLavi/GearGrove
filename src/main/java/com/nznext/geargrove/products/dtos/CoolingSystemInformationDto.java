package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.coolsys.FanDiameter;
import com.nznext.geargrove.products.enums.coolsys.Purposes;
import com.nznext.geargrove.products.enums.ssd.Types;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoolingSystemInformationDto extends ProductDto{
    Purposes purpose;
    Types type;
    FanDiameter fanDiameter;

    public CoolingSystemInformationDto(String productName, String description, Double price, Integer quantity,
                                       String supplier, Integer year, Purposes purpose, Types type, FanDiameter fanDiameter) {
        super(productName, description, price, quantity, supplier, year);
        this.purpose = purpose;
        this.type = type;
        this.fanDiameter = fanDiameter;
    }
}
