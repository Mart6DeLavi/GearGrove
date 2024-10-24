package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.harddrive.ClipBoardSize;
import com.nznext.geargrove.products.enums.harddrive.Connections;
import com.nznext.geargrove.products.enums.harddrive.FormFactors;
import com.nznext.geargrove.products.enums.harddrive.Types;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareInformationDto extends ProductDto{
    Types type;
    int capacity;
    FormFactors formFactor;
    int speed;
    Connections connection;
    ClipBoardSize clipBoardSize;

    public HardwareInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, Types type, int capacity, FormFactors formFactor, int speed, Connections connection, ClipBoardSize clipBoardSize) {
        super(productName, description, price, quantity, supplier, year);
        this.type = type;
        this.capacity = capacity;
        this.formFactor = formFactor;
        this.speed = speed;
        this.connection = connection;
        this.clipBoardSize = clipBoardSize;
    }
}
