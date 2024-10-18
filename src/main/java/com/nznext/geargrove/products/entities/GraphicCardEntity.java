package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.graphcards.BusBitDepth;
import com.nznext.geargrove.products.enums.graphcards.ConnectionInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "graphic_cards")
public class GraphicCardEntity extends Product {

    @Column(name = "memory", nullable = false)
    private int memory;

    @Column(name = "memory_type", nullable = false)
    private String memoryType;

    @Column(name = "take_up_slots", nullable = false)
    private int takeUpSlots;

    @Enumerated(EnumType.STRING)
    @Column(name = "connection_interface", nullable = false)
    private ConnectionInterface connectionInterface;

    @Column(name = "number_of_monitors", nullable = false)
    private int numberOfMonitors;

    @Column(name = "number_of_fans", nullable = false)
    private int numberOfFans;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "additional_power_supply", nullable = false)
    private int additionalPower;

    @Enumerated(EnumType.STRING)
    @Column(name = "bus_bit_depth", nullable = false)
    private BusBitDepth busBitDepth;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        GraphicCardEntity that = (GraphicCardEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
