package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.motherboard.*;
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
@Table(name = "mother_boards")
public class MotherBoardEntity extends Product{

    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    private FormFactors formFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false)
    private Sockets socket;

    @Enumerated(EnumType.STRING)
    @Column(name = "chipset",nullable = false)
    private Chipsets chipset;

    @Enumerated(EnumType.STRING)
    @Column(name = "ram_type", nullable = false)
    private RAMTypes ramType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ram_capacity", nullable = false)
    private RAMCapacity ramCapacity;

    @Column(name = "frequency", nullable = false)
    private int frequency;

    @Column(name = "sata_ports", nullable = false)
    private int sataPorts;

    @Column(name = "m2_ports", nullable = false)
    private int m2Ports;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MotherBoardEntity that = (MotherBoardEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
