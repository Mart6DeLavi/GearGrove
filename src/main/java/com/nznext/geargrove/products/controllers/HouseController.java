package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.HouseInformationDto;
import com.nznext.geargrove.products.entities.HouseEntity;
import com.nznext.geargrove.products.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping("/create")
    public ResponseEntity<HouseEntity> createNewProduct(@RequestBody HouseEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(houseService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<HouseInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return houseService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<HouseEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody HouseEntity updateProduct
    ) {
        return houseService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        houseService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
