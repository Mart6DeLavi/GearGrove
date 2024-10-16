package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.GraphicCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GraphicCardRepository extends JpaRepository<GraphicCardEntity, Integer> {

    @Query("SELECT product FROM GraphicCardEntity product WHERE product.productName = :productName")
    Optional<GraphicCardEntity> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM GraphicCardEntity product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
