package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.MotherBoardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotherBoardRepository extends ProductRepository<MotherBoardEntity> {
    @Query("SELECT product FROM MotherBoardEntity product WHERE product.id = :productId")
    Optional<MotherBoardEntity> findProductByProductId(@Param("productId") Integer productId);
}
