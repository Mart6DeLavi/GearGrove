package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.RamInformationDto;
import com.nznext.geargrove.products.entities.RamEntity;
import com.nznext.geargrove.products.service.RamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/rams")
@RequiredArgsConstructor
public class RamController {

    private final RamService ramService;

    @PostMapping("/create")
    public ResponseEntity<RamEntity> createNewProduct(@RequestBody RamEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ramService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<RamInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return ramService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<RamEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody RamEntity updateProduct
    ) {
        return ramService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        ramService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
