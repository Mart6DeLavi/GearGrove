package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import com.nznext.geargrove.products.entities.RamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RamRepository extends ProductRepository<RamEntity>{
    @Query("SELECT product FROM RamEntity product WHERE product.id = :productId")
    Optional<RamEntity> findProductByProductId(@Param("productId") Integer productId);

}
