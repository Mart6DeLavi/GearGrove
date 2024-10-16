package com.nznext.geargrove.products.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cpu_s")
public class CPUEntity extends Product{

    @Column(name = "socket", nullable = false)
    private String socket;

    @Column(name = "threads", nullable = false)
    private int threads;

    @Column(name = "cores", nullable = false)
    private int cores;

    @Column(name = "frequency", nullable = false)
    private Double frequency;

    @Column(name = "technical_process", nullable = false)
    private int technicalProcess;

    @Column(name = "TDP", nullable = false)
    private int TDP;

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CPUEntity cpuEntity = (CPUEntity) object;
        return getId() != null && Objects.equals(getId(), cpuEntity.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
