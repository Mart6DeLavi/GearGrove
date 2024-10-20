package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import com.nznext.geargrove.products.entities.GraphicCardEntity;
import com.nznext.geargrove.products.entities.HardwareEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HardwareRepository extends ProductRepository<HardwareEntity> {
    @Query("SELECT product FROM GraphicCardEntity product WHERE product.id = :productId")
    Optional<GraphicCardEntity> findProductByProductId(@Param("productId") Integer productId);
}
