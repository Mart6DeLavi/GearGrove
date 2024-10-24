package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.HardwareInformationDto;
import com.nznext.geargrove.products.entities.HardwareEntity;
import com.nznext.geargrove.products.service.HardwareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/hardwares")
@RequiredArgsConstructor
public class HardwareController {

    private final HardwareService hardwareService;

    @PostMapping("/create")
    public ResponseEntity<HardwareEntity> createNewProduct(@RequestBody HardwareEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hardwareService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<HardwareInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return hardwareService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<HardwareEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody HardwareEntity updateProduct
    ) {
        return hardwareService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        hardwareService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
