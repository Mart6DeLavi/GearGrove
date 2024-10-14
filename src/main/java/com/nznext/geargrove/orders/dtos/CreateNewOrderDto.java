package com.nznext.geargrove.orders.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateNewOrderDto {
    private Date orderDate;
    private String status;
    private Double sum;
}
