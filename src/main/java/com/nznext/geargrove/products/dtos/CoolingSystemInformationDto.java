package com.nznext.geargrove.products.dtos;

import com.nznext.geargrove.products.enums.coolsys.FanDiameter;
import com.nznext.geargrove.products.enums.coolsys.Purposes;
import com.nznext.geargrove.products.enums.ssd.Types;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) for cooling system product information.
 *
 * <p>This class extends {@link ProductDto} and contains additional details specific to cooling systems, such as
 * the purpose, type, and fan diameter of the product.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Getter} - Automatically generates getter methods for all fields.</li>
 *   <li>{@code @Setter} - Automatically generates setter methods for all fields.</li>
 *   <li>{@code @FieldDefaults} - Specifies the default access level for fields, in this case, {@code PRIVATE}.</li>
 * </ul>
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoolingSystemInformationDto extends ProductDto {

    /** The intended purpose of the cooling system (e.g., "Residential", "Industrial"). */
    Purposes purpose;

    /** The type of the cooling system (e.g., "Air Conditioner", "Fan"). */
    Types type;

    /** The diameter of the fan in the cooling system. */
    FanDiameter fanDiameter;

    /**
     * Constructs a new {@link CoolingSystemInformationDto} with the specified product details and cooling system-specific information.
     *
     * @param productName the name of the product.
     * @param description a brief description of the product.
     * @param price the price of the product.
     * @param quantity the available quantity of the product.
     * @param supplier the name of the supplier of the product.
     * @param year the year the product was manufactured.
     * @param purpose the intended purpose of the cooling system.
     * @param type the type of the cooling system.
     * @param fanDiameter the diameter of the fan in the cooling system.
     */
    public CoolingSystemInformationDto(String productName, String description, Double price, Integer quantity,
                                       String supplier, Integer year, Purposes purpose, Types type, FanDiameter fanDiameter) {
        super(productName, description, price, quantity, supplier, year);
        this.purpose = purpose;
        this.type = type;
        this.fanDiameter = fanDiameter;
    }
}
