package com.nznext.geargrove.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class FindProductByproductNameInformationDto {
    private String productName;
    private Double price;
    private String description;
}
