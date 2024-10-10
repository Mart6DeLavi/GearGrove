package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT product FROM Product product WHERE product.productName = :productName ")
    Optional<Product> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM Product product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}
