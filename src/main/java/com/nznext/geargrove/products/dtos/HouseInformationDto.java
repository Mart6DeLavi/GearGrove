package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.houses.DustFilter;
import com.nznext.geargrove.products.enums.houses.FormFactors;
import com.nznext.geargrove.products.enums.houses.HousingPlacement;
import com.nznext.geargrove.products.enums.houses.MotherBoardTypes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HouseInformationDto extends ProductDto{
    FormFactors formFactor;
    MotherBoardTypes motherBoardType;
    HousingPlacement housingPlacement;
    boolean isPowerUnit;
    String additionalFunctions;
    DustFilter dustFilter;

    public HouseInformationDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year, FormFactors formFactor, MotherBoardTypes motherBoardType, HousingPlacement housingPlacement, boolean isPowerUnit, String additionalFunctions, DustFilter dustFilter) {
        super(productName, description, price, quantity, supplier, year);
        this.formFactor = formFactor;
        this.motherBoardType = motherBoardType;
        this.housingPlacement = housingPlacement;
        this.isPowerUnit = isPowerUnit;
        this.additionalFunctions = additionalFunctions;
        this.dustFilter = dustFilter;
    }
}
