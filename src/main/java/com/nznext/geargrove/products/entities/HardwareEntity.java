package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.harddrive.Connections;
import com.nznext.geargrove.products.enums.harddrive.FormFactors;
import com.nznext.geargrove.products.enums.harddrive.Types;
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
@Table(name = "hardwares")
public class HardwareEntity extends Product{

    @Enumerated(EnumType.STRING)
    @Column(name = "types", nullable = false)
    private Types type;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_factors", nullable = false)
    private FormFactors formFactors;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Enumerated(EnumType.STRING)
    @Column(name = "connection", nullable = false)
    private Connections connection;

    @Enumerated(EnumType.STRING)
    @Column(name = "clip_board_size", nullable = false)
    private int clipboardSize;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HardwareEntity that = (HardwareEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
