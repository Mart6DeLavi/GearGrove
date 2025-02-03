package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CPUEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link CPUEntity} entities in the database.
 *
 * <p>This interface extends {@link ProductRepository} and provides specific methods for managing CPU products,
 * including retrieving a product by its ID and fetching the quantity of a product.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring Data repository, enabling exception translation
 *   and making it eligible for dependency injection.</li>
 * </ul>
 */
@Repository
public interface CpuRepository extends ProductRepository<CPUEntity> {

    /**
     * Finds a CPU product by its product ID.
     *
     * <p>This method retrieves a {@link CPUEntity} based on its product ID. If no product with the given ID exists,
     * it returns an empty {@link Optional}.</p>
     *
     * @param productId the ID of the product to retrieve.
     * @return an {@link Optional} containing the {@link CPUEntity} if found, or {@link Optional#empty()} if not.
     */
    @Query("SELECT product FROM CPUEntity product WHERE product.id = :productId")
    Optional<CPUEntity> findProductByProductId(@Param("productId") Integer productId);

    /**
     * Retrieves the total quantity of a CPU product by its product ID.
     *
     * <p>This method returns the sum of the quantities for the CPU product with the given product ID.
     * If no products are found, it returns 0.</p>
     *
     * @param productId the ID of the product to fetch the quantity for.
     * @return the total quantity of the product.
     */
    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM #{#entityName} product WHERE product.id = :productId")
    Integer quantityByProductId(@Param("productId") Integer productId);
}