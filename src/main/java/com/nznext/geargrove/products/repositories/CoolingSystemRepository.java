package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoolingSystemRepository extends ProductRepository<CoolingSystemEntity> {
    @Query("SELECT product FROM CoolingSystemEntity product WHERE product.id = :productId")
    Optional<CoolingSystemEntity> findProductByProductId(@Param("productId") Integer productId);
}
