package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.SSDInformationDto;
import com.nznext.geargrove.products.entities.SSDEntity;
import com.nznext.geargrove.products.service.SSDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/ssd")
@RequiredArgsConstructor
public class SSDController {

    private final SSDService ssdService;

    @PostMapping("/create")
    public ResponseEntity<SSDEntity> createNewProduct(@RequestBody SSDEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ssdService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<SSDInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return ssdService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<SSDEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody SSDEntity updateProduct
    ) {
        return ssdService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        ssdService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
