package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.CpuInformationDto;
import com.nznext.geargrove.products.entities.CPUEntity;
import com.nznext.geargrove.products.service.CpuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/cpus")
@RequiredArgsConstructor
public class CpuController {

    private final CpuService cpuService;

    @PostMapping("/create")
    public ResponseEntity<CPUEntity> createNewProduct(@RequestBody CPUEntity cpuEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cpuService.createNewProduct(cpuEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<CpuInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return cpuService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<CPUEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody CPUEntity updateProduct
    ) {
        return cpuService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        cpuService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
