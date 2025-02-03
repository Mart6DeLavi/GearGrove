package com.nznext.geargrove.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a role in the GearGrove application.
 *
 * <p>This class is mapped to the {@code roles} table in the database and represents the roles
 * that can be assigned to users within the application.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Entity} - Marks this class as a JPA entity, meaning it will be mapped to a table in the database.</li>
 *   <li>{@code @Table} - Specifies the name of the table in the database ({@code roles}) that this entity corresponds to.</li>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generate getter and setter methods for the fields.</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    /** The unique identifier for the role. */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The name of the role (e.g., "ADMIN", "USER"). */
    @Column(name = "name")
    private String name;
}
