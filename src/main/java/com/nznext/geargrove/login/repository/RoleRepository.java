package com.nznext.geargrove.login.repository;

import com.nznext.geargrove.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link Role} entities in the database.
 *
 * <p>This interface extends {@link JpaRepository} to provide basic CRUD operations for {@link Role} entities.</p>
 *
 * <p>Additional custom query methods are defined to find a role by its name and to check if a role with a
 * specific name exists.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring Data repository, making it eligible for
 *   exception translation and enabling it to be injected into other Spring components.</li>
 * </ul>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Finds a {@link Role} by its name.
     *
     * <p>This method retrieves a {@link Role} entity from the database based on the specified role name.</p>
     *
     * @param name the name of the role to find.
     * @return the {@link Role} entity with the given name, or {@code null} if no role with that name exists.
     */
    Role findByName(String name);

    /**
     * Checks if a {@link Role} with the specified name exists.
     *
     * <p>This method checks the existence of a role in the database by its name.</p>
     *
     * @param name the name of the role to check for existence.
     * @return {@code true} if a role with the given name exists, otherwise {@code false}.
     */
    boolean existsByName(String name);
}
