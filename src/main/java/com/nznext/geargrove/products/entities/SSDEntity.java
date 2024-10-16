package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.ssd.Interface;
import com.nznext.geargrove.products.enums.ssd.Types;
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
@Table(name = "ssd")
public class SSDEntity extends Product{

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Types type;

    @Column(name = "storage_capacity", nullable = false)
    private int storageCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "interface", nullable = false)
    private Interface interface_;

    @Column(name = "memory_type", nullable = false)
    private Types memoryType;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SSDEntity ssdEntity = (SSDEntity) object;
        return getId() != null && Objects.equals(getId(), ssdEntity.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
