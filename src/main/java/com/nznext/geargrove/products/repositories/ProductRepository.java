package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@NoRepositoryBean
public interface ProductRepository<T extends Product> extends JpaRepository<T, Integer> {

    @Query("SELECT product FROM #{#entityName} product WHERE product.productName = :productName")
    Optional<T> findProductByProductName(@Param("productName") String productName);

    @Query("SELECT COALESCE(SUM(product.quantity), 0) FROM #{#entityName} product WHERE product.productName = :productName")
    Integer quantityByProductName(@Param("productName") String productName);
}

