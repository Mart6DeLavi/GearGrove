package com.nznext.geargrove.products.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateNewProductDto {
    String productName;
    String description;
    Double price;
    Integer quantity;
    String supplier;
}
