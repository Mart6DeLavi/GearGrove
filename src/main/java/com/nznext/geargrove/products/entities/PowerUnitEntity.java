package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.powerunit.*;
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
@Table(name = "power_units")
public class PowerUnitEntity extends Product {

    @Column(name = "power", nullable = false)
    private int power;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    private FormFactors formFactors;

    @Enumerated(EnumType.STRING)
    @Column(name = "certificate", nullable = false)
    private Certificate certificate;

    @Enumerated(EnumType.STRING)
    @Column(name = "cabell_system", nullable = false)
    private CabellSystem cabellSystem;

    @Enumerated(EnumType.STRING)
    @Column(name = "fan_diameter", nullable = false)
    private FanDiameter fanDiameter;

    @Enumerated(EnumType.STRING)
    @Column(name = "warranty", nullable = false)
    private Warranty warranty;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PowerUnitEntity that = (PowerUnitEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
