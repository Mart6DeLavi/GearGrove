package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.PowerUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PowerUnitRepository extends JpaRepository<PowerUnitEntity, Integer> {

    @Query("SELECT product FROM PowerUnitEntity product WHERE product.productName = :productName")
    Optional<PowerUnitEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM PowerUnitEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
