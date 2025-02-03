package com.nznext.geargrove.products.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) for CPU product information.
 *
 * <p>This class extends {@link ProductDto} and contains additional details specific to CPU products, such as
 * the socket type, number of threads, cores, frequency, technical process, and thermal design power (TDP).</p>
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
public class CpuInformationDto extends ProductDto {

    /** The socket type compatible with the CPU (e.g., "LGA1151", "AM4"). */
    String socket;

    /** The number of threads supported by the CPU. */
    int threads;

    /** The number of cores in the CPU. */
    int cores;

    /** The base frequency of the CPU in GHz. */
    Double frequency;

    /** The technical process used to manufacture the CPU (in nanometers). */
    int technicalProcess;

    /** The thermal design power (TDP) of the CPU in watts. */
    int TDP;

    /**
     * Constructs a new {@link CpuInformationDto} with the specified product details and CPU-specific information.
     *
     * @param productName the name of the product.
     * @param description a brief description of the product.
     * @param price the price of the product.
     * @param quantity the available quantity of the product.
     * @param supplier the name of the supplier of the product.
     * @param year the year the product was manufactured.
     * @param socket the socket type of the CPU.
     * @param threads the number of threads supported by the CPU.
     * @param cores the number of cores in the CPU.
     * @param frequency the base frequency of the CPU.
     * @param technicalProcess the technical process used to manufacture the CPU (in nanometers).
     * @param TDP the thermal design power (TDP) of the CPU.
     */
    public CpuInformationDto(String productName, String description, Double price,
                             Integer quantity, String supplier, Integer year,
                             String socket, int threads, int cores,
                             Double frequency, int technicalProcess, int TDP) {
        super(productName, description, price, quantity, supplier, year);
        this.socket = socket;
        this.threads = threads;
        this.cores = cores;
        this.frequency = frequency;
        this.technicalProcess = technicalProcess;
        this.TDP = TDP;
    }
}
