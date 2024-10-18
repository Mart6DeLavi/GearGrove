package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.GraphicCardEntity;
import com.nznext.geargrove.products.entities.HardwareEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends ProductRepository<HardwareEntity> {
}
