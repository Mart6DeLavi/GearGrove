package com.nznext.geargrove.products.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CpuInformationDto extends ProductDto {
    String socket;
    int threads;
    int cores;
    Double frequency;
    int technicalProcess;
    int TDP;

    public CpuInformationDto(String productName, String description, Double price,
                             Integer quantity, String supplier, Integer year,
                             String socket, int threads, int cores,
                             Double frequency, int technicalProcess, int TDP) {
        super(productName, description, price, quantity, supplier, year);
        this.socket = socket;
        this.threads = threads;
        this.cores = cores;
        this.frequency = frequency;
        this.technicalProcess = technicalProcess;
        this.TDP = TDP;
    }
}
