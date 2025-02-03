package com.nznext.geargrove.login.repository;

import com.nznext.geargrove.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link UserEntity} entities in the database.
 *
 * <p>This interface extends {@link JpaRepository} to provide basic CRUD operations for {@link UserEntity} entities.</p>
 *
 * <p>Additional custom query methods are defined to find a user entity by its username.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring Data repository, making it eligible for
 *   exception translation and enabling it to be injected into other Spring components.</li>
 * </ul>
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a {@link UserEntity} by its username.
     *
     * <p>This method retrieves a {@link UserEntity} from the database based on the specified username.</p>
     *
     * @param username the username of the user to find.
     * @return an {@link Optional} containing the {@link UserEntity} if found, or {@code Optional.empty()} if no user with that username exists.
     */
    Optional<UserEntity> findUserEntityByUsername(String username);
}
