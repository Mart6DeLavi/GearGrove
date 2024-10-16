package com.nznext.geargrove.products.entities;

import com.nznext.geargrove.products.enums.powerunit.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
