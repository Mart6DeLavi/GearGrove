package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.PowerUnitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PowerUnitRepository extends ProductRepository<PowerUnitEntity> {
    @Query("SELECT product FROM PowerUnitEntity product WHERE product.id = :productId")
    Optional<PowerUnitEntity> findProductByProductId(@Param("productId") Integer productId);
}
