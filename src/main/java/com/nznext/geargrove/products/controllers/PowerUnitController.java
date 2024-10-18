package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.PowerUnitEntity;
import com.nznext.geargrove.products.service.PowerUnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/power-units")
@RequiredArgsConstructor
public class PowerUnitController {

    private final PowerUnitService powerUnitService;

    @PostMapping("/create")
    public ResponseEntity<PowerUnitEntity> createNewProduct(@RequestBody PowerUnitEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(powerUnitService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productName}")
    public CompletableFuture<ResponseEntity<FindProductByproductNameInformationDto>> findProduct(
            @PathVariable String productName
    ) {
        return powerUnitService.findProductByProductName(productName)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<PowerUnitEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody PowerUnitEntity updateProduct
    ) {
        return powerUnitService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        powerUnitService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
