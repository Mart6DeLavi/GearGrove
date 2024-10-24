package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.GraphicCardEntity;
import com.nznext.geargrove.products.entities.HardwareEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HardwareRepository extends ProductRepository<HardwareEntity> {
    @Query("SELECT product FROM HardwareEntity product WHERE product.id = :productId")
    Optional<HardwareEntity> findProductByProductId(@Param("productId") Integer productId);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM #{#entityName} product WHERE product.id = :productId")
    Integer quantityByProductId(@Param("productId") Integer productId);
}
