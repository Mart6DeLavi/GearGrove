package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.powerunit.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PowerUnitInformationDto extends ProductDto{
    int power;
    FormFactors formFactor;
    Certificate certificate;
    CabellSystem cabellSystem;
    FanDiameter fanDiameter;
    Warranty warranty;

    public PowerUnitInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, int power, FormFactors formFactor, Certificate certificate, CabellSystem cabellSystem, FanDiameter fanDiameter, Warranty warranty) {
        super(productName, description, price, quantity, supplier, year);
        this.power = power;
        this.formFactor = formFactor;
        this.certificate = certificate;
        this.cabellSystem = cabellSystem;
        this.fanDiameter = fanDiameter;
        this.warranty = warranty;
    }
}
