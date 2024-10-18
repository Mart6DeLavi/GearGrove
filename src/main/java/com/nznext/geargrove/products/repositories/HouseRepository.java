package com.nznext.geargrove.products.repositories;

import com.nznext.geargrove.products.entities.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository extends ProductRepository<HouseEntity> {
}
