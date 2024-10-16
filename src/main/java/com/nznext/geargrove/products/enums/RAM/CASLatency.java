package com.nznext.geargrove.products.enums.RAM;

import lombok.Getter;

@Getter
public enum CASLatency {
    CL9("CL9"),
    CL10("CL10"),
    CL11("CL11"),
    CL14("CL14"),
    CL15("CL15"),
    CL16("CL16"),
    CL17("CL17"),
    CL18("CL18"),
    CL19("CL19"),
    CL20("CL20"),
    CL21("CL21"),
    CL22("CL22"),
    CL30("CL30"),
    CL32("CL32"),
    CL36("CL36"),
    CL38("CL38"),
    CL40("CL40"),
    CL42("CL42"),
    CL46("CL46");

    private final String name;

     CASLatency(final String name) {
        this.name = name;
    }
}
