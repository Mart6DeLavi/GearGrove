package com.nznext.geargrove.products.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


/**
 * Abstract class representing a general product in the GearGrove application.
 *
 * <p>This class serves as a base for various types of products (e.g., CPU, CoolingSystem) and provides common
 * properties that all products share, such as product name, description, price, quantity, supplier, and year.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @MappedSuperclass} - Marks this class as a superclass for other entities, meaning the fields
 *   of this class will be inherited by other entities, but this class itself will not be mapped to a database table.</li>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generate getter and setter methods for all fields.</li>
 * </ul>
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Product {

    /** The unique identifier for the product. */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** The name of the product. */
    @Column(name = "product_name", nullable = false)
    private String productName;

    /** A description of the product. */
    @Column(name = "description", length = 300)
    private String description;

    /** The price of the product. */
    @Column(name = "price", nullable = false)
    private Double price;

    /** The available quantity of the product. */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /** The name of the product's supplier. */
    @Column(name = "supplier", nullable = false, length = 100)
    private String supplier;

    /** The year the product was manufactured. */
    @Column(name = "year", nullable = false)
    private Integer year;
}