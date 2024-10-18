package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.RAM.CASLatency;
import com.nznext.geargrove.products.enums.RAM.OperatingVoltage;
import com.nznext.geargrove.products.enums.RAM.RamType;
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
@Table(name = "rams")
public class RamEntity extends Product{

    @Column(name = "memory_capacity", nullable = false)
    private int memoryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RamType type;

    @Column(name = "clock_speed", nullable = false)
    private int clockSpeed;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_latency", nullable = false)
    private CASLatency casLatency;

    @Enumerated(EnumType.STRING)
    @Column(name = "operating_voltage", nullable = false)
    private OperatingVoltage operatingVoltage;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RamEntity ramEntity = (RamEntity) object;
        return getId() != null && Objects.equals(getId(), ramEntity.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
