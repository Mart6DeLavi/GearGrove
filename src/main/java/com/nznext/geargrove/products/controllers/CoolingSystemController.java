package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import com.nznext.geargrove.products.service.CoolingSystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/coolingsys")
@RequiredArgsConstructor
public class CoolingSystemController {

    private final CoolingSystemService coolingSystemService;

    @PostMapping("/create")
    public ResponseEntity<CoolingSystemEntity> createNewProduct(@RequestBody CoolingSystemEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coolingSystemService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productName}")
    public CompletableFuture<ResponseEntity<FindProductByproductNameInformationDto>> findProduct(
            @PathVariable String productName
    ) {
        return coolingSystemService.findProductByProductName(productName)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<CoolingSystemEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody CoolingSystemEntity updateProduct
    ) {
        return coolingSystemService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        coolingSystemService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
