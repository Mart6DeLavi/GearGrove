package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CPUEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CpuRepository extends ProductRepository<CPUEntity> {
    @Query("SELECT product FROM CPUEntity product WHERE product.id = :productId")
    Optional<CPUEntity> findProductByProductId(@Param("productId") Integer productId);
}
