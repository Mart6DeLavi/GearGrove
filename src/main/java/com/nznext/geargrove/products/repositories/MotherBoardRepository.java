package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.MotherBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotherBoardRepository extends JpaRepository<MotherBoardEntity, Long> {

    @Query("SELECT product FROM MotherBoardEntity product WHERE product.productName = :productName")
    Optional<MotherBoardEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM MotherBoardEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
