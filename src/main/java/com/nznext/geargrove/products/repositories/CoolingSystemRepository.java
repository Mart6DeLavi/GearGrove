package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link CoolingSystemEntity} entities in the database.
 *
 * <p>This interface extends {@link ProductRepository} with specific methods for managing cooling system products,
 * including retrieving a product by its ID and fetching the quantity of a product.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring Data repository, enabling exception translation
 *   and making it eligible for dependency injection.</li>
 * </ul>
 */
@Repository
public interface CoolingSystemRepository extends ProductRepository<CoolingSystemEntity> {

    /**
     * Finds a cooling system product by its product ID.
     *
     * <p>This method retrieves a {@link CoolingSystemEntity} based on its product ID.
     * If no product with the given ID exists, it returns an empty {@link Optional}.</p>
     *
     * @param productId the ID of the product to retrieve.
     * @return an {@link Optional} containing the {@link CoolingSystemEntity} if found, or {@link Optional#empty()} if not.
     */
    @Query("SELECT product FROM CoolingSystemEntity product WHERE product.id = :productId")
    Optional<CoolingSystemEntity> findProductByProductId(@Param("productId") Integer productId);

    /**
     * Retrieves the total quantity of a cooling system product by its product ID.
     *
     * <p>This method returns the sum of the quantities for the cooling system product with the given product ID.
     * If no products are found, it returns 0.</p>
     *
     * @param productId the ID of the product to fetch the quantity for.
     * @return the total quantity of the product.
     */
    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM #{#entityName} product WHERE product.id = :productId")
    Integer quantityByProductId(@Param("productId") Integer productId);
}
