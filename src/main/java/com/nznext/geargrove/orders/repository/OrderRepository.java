package com.nznext.geargrove.orders.repository;

import com.nznext.geargrove.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Order} entities in the database.
 *
 * <p>This interface extends {@link JpaRepository}, providing CRUD operations for {@link Order} entities
 * and enabling easy access to the database for managing orders.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring Data repository, enabling exception translation
 *   and making it eligible for dependency injection into other Spring components.</li>
 * </ul>
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
