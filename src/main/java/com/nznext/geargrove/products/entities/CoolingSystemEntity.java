package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.coolsys.FanDiameter;
import com.nznext.geargrove.products.enums.coolsys.Purposes;
import com.nznext.geargrove.products.enums.ssd.Types;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Entity representing a cooling system product in the GearGrove application.
 *
 * <p>This class is mapped to the {@code cooling_systems} table in the database and contains specific details
 * about a cooling system, including its purpose, type, and fan diameter. It extends from {@link Product}, which
 * provides general product details.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Entity} - Marks this class as a JPA entity, mapping it to a table in the database.</li>
 *   <li>{@code @Table} - Specifies the name of the table in the database (in this case, {@code cooling_systems}).</li>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generate getter and setter methods for all fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required fields (final fields),
 *   enabling dependency injection and easier instantiation.</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cooling_systems")
public class CoolingSystemEntity extends Product {

    /** The intended purpose of the cooling system (e.g., "Residential", "Industrial"). */
    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private Purposes purpose;

    /** The type of the cooling system (e.g., "Air Conditioner", "Fan"). */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Types type;

    /** The diameter of the fan in the cooling system. */
    @Enumerated(EnumType.STRING)
    @Column(name = "fan_diameter", nullable = false)
    private FanDiameter fanDiameter;

    /**
     * Compares this cooling system product with another object for equality.
     *
     * <p>This method checks if the given object is equal to this cooling system product based on its product ID.</p>
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
        CoolingSystemEntity that = (CoolingSystemEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    /**
     * Computes the hash code for this cooling system product.
     *
     * <p>This method returns the hash code of the cooling system product, based on its class and ID.</p>
     *
     * @return the hash code of this cooling system product.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}