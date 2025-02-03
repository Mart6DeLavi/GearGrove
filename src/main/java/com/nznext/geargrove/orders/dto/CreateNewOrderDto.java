package com.nznext.geargrove.orders.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for creating a new order.
 *
 * <p>This class is used to transfer the data necessary for creating a new order in the system.
 * It contains the order's date, status, and total sum.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Getter} - Automatically generates getter methods for all fields.</li>
 *   <li>{@code @Setter} - Automatically generates setter methods for all fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required final fields, enabling
 *   dependency injection and easier instantiation.</li>
 * </ul>
 */
@Getter
@Setter
@RequiredArgsConstructor
public class CreateNewOrderDto {

    /** The date when the order was placed. */
    private Date orderDate;

    /** The status of the order (e.g., "Pending", "Shipped"). */
    private String status;

    /** The total amount of the order. */
    private Double sum;
}