package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.MotherBoardEntity;
import com.nznext.geargrove.products.service.MotherBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/motherboards")
@RequiredArgsConstructor
public class MotherBoardController {

    private final MotherBoardService motherBoardService;

    @PostMapping("/create")
    public ResponseEntity<MotherBoardEntity> createNewProduct(@RequestBody MotherBoardEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motherBoardService.createNewProduct(coolingSystemEntity));
    }

    @GetMapping("/info/{productName}")
    public CompletableFuture<ResponseEntity<FindProductByproductNameInformationDto>> findProduct(
            @PathVariable String productName
    ) {
        return motherBoardService.findProductByProductName(productName)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<MotherBoardEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody MotherBoardEntity updateProduct
    ) {
        return motherBoardService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        motherBoardService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
