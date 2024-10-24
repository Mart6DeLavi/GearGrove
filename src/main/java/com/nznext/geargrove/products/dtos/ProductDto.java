package com.nznext.geargrove.products.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ProductDto {
    String productName;
    String description;
    Double price;
    Integer quantity;
    String supplier;
    Integer year;

    public ProductDto(String productName, String description, Double price, Integer quantity, String supplier, Integer year) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
        this.year = year;
    }
}
