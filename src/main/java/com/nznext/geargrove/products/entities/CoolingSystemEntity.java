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

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cooling_systems")
public class CoolingSystemEntity extends Product {



    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private Purposes purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Types type;

    @Enumerated(EnumType.STRING)
    @Column(name = "fan_diameter", nullable = false)
    private FanDiameter fanDiameter;

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

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
