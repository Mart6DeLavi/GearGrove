package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RamRepository extends JpaRepository<RamEntity, Integer> {

    @Query("SELECT product FROM RamEntity product WHERE product.productName = :productName")
    Optional<RamEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM RamEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
