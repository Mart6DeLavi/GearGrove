package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.GraphicCardInformationDto;
import com.nznext.geargrove.products.entities.GraphicCardEntity;
import com.nznext.geargrove.products.service.GraphicCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/products/graph-cards")
@RequiredArgsConstructor
public class GraphicCardController {

    private final GraphicCardService graphicCardService;

    @PostMapping("/create")
    public ResponseEntity<GraphicCardEntity> createNewProduct(@RequestBody GraphicCardEntity graphicCardEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(graphicCardService.createNewProduct(graphicCardEntity));
    }

    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<GraphicCardInformationDto>> findProduct(
            @PathVariable Integer productId
    ) {
        return graphicCardService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<GraphicCardEntity> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody GraphicCardEntity updateProduct
    ) {
        return graphicCardService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        graphicCardService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}
