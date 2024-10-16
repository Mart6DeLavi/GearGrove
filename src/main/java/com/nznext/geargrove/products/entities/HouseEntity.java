package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.houses.DustFilter;
import com.nznext.geargrove.products.enums.houses.FormFactors;
import com.nznext.geargrove.products.enums.houses.HousingPlacement;
import com.nznext.geargrove.products.enums.houses.MotherBoardTypes;
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
@Table(name = "houses")
public class HouseEntity extends Product {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "form_factor", nullable = false)
    private FormFactors formFactor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "motherboard", nullable = false)
    private MotherBoardTypes motherBoardType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "house_placement", nullable = false)
    private HousingPlacement housingPlacement;

    @Column(name = "is_power_unit", nullable = false)
    private boolean isPowerUnit;

    @Column(name = "additional_functions")
    private String additionalFunctions;

    @Enumerated(EnumType.STRING)
    @Column(name = "dust_filter", nullable = false)
    private DustFilter dustFilter;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HouseEntity that = (HouseEntity) object;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
