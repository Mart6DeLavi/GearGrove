package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.CreateNewProductDto;
import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.Product;
import com.nznext.geargrove.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public Product createNewProduct(@RequestBody CreateNewProductDto createNewProductDto) {
        return productService.createNewProduct(createNewProductDto);
    }

    @GetMapping("/info/{productName}")
    public CompletableFuture<Optional<FindProductByproductNameInformationDto>> findProduct(@PathVariable String productName) {
        return productService.findProductByProductName(productName);
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<Product> updateProductInformation(
            @PathVariable Integer productId,
            @RequestBody Product updateProduct
    ) {
        return productService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
    }
}
