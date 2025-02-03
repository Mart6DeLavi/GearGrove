package com.nznext.geargrove.orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Entity representing an order in the GearGrove application.
 *
 * <p>This class is mapped to the {@code orders} table in the database and contains information about the order,
 * including the username of the customer, the order date, status, total sum, and associated orders.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Entity} - Marks this class as a JPA entity, mapping it to a table in the database.</li>
 *   <li>{@code @Table} - Specifies the name of the table in the database (in this case, {@code orders}).</li>
 *   <li>{@code @Getter} and {@code @Setter} - Automatically generate getter and setter methods for all fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required fields (final fields),
 *   enabling dependency injection and easier instantiation.</li>
 * </ul>
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "orders")
public class Order {

    /** The unique identifier for the order. */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /** The username associated with the order. */
    @Column(name = "username", nullable = false)
    private String username;

    /** The date the order was placed. */
    @Column(name = "date", nullable = false)
    private Date orderDate;

    /** The current status of the order (e.g., "Pending", "Shipped"). */
    @Column(name = "status", nullable = false)
    private String status;

    /** The total sum of the order. */
    @Column(name = "sum", nullable = false)
    private Double sum;

    /**
     * A collection of related orders, representing a many-to-many relationship.
     * This relationship is mapped through the {@code user_orders} join table.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Collection<Order> orders;

    /**
     * Compares this order with another object for equality.
     *
     * <p>This method checks if the given object is equal to this order based on the order ID.</p>
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
        Order order = (Order) object;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    /**
     * Computes the hash code for this order.
     *
     * <p>This method returns the hash code of the order, based on its class and ID.</p>
     *
     * @return the hash code of this order.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}