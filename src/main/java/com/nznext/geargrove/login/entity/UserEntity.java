package com.nznext.geargrove.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * Entity representing a user in the GearGrove application.
 *
 * <p>This class is mapped to the {@code users} table in the database and contains information
 * about a user, including their username, email, password, registration date, address, and roles.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Entity} - Marks this class as a JPA entity, mapping it to a database table.</li>
 *   <li>{@code @Table} - Specifies the name of the table in the database (in this case, {@code users}).</li>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generates getter and setter methods for the fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor with required fields (fields marked as final).</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users")
public class UserEntity {

    /** The unique identifier for the user. */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /** The unique username of the user. */
    @Column(name = "username", nullable = false, unique = true, length = 60)
    private String username;

    /** The email address of the user. */
    @Column(name = "email", nullable = false)
    private String email;

    /** The password of the user. */
    @Column(name = "password", nullable = false)
    private String password;

    /** The registration date of the user. */
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    /** The address of the user. */
    @Column(name = "address", nullable = false, length = 30)
    private String address;

    /** The roles assigned to the user. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    /**
     * Compares this user with another object for equality.
     *
     * <p>This method checks if the given object is equal to this user entity based on the user ID.</p>
     *
     * @param object the object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserEntity that = (UserEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    /**
     * Computes the hash code for this user entity.
     *
     * <p>This method returns the hash code of the user entity, which is based on the class
     * and the ID of the user.</p>
     *
     * @return the hash code of this user entity.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}