package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.SSDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SSDRepository extends JpaRepository<SSDEntity, Integer> {

    @Query("SELECT product FROM SSDEntity product WHERE product.productName = :productName")
    Optional<SSDEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM SSDEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
