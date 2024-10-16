package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CpuRepository extends JpaRepository<CPUEntity, Long> {

    @Query("SELECT product FROM CPUEntity product WHERE product.productName = :productName")
    Optional<CPUEntity> findByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM CPUEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
