package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoolingSystemRepository extends JpaRepository<CoolingSystemEntity, Long> {

    @Query("SELECT product FROM CoolingSystemEntity product WHERE product.productName = :productName")
    Optional<CoolingSystemEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM CoolingSystemEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
