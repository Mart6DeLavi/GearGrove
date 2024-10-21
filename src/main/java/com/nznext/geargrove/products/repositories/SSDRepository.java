package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.SSDEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SSDRepository extends ProductRepository<SSDEntity> {
    @Query("SELECT product FROM SSDEntity product WHERE product.id = :productId")
    Optional<SSDEntity> findProductByProductId(@Param("productId") Integer productId);
}
