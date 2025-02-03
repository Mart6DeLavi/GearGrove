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

/**
 * Controller for managing CPU-related products in the GearGrove application.
 *
 * <p>This controller provides endpoints for creating, retrieving, updating, and deleting CPU products.
 * It interacts with the {@link CpuService} to perform business logic related to CPU products.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 *   <li>{@code @RestController} - Marks this class as a REST controller, where methods return objects that are automatically serialized to JSON or XML (depending on the client).</li>
 *   <li>{@code @RequestMapping} - Specifies the base URL path for the endpoints in this controller ({@code /products/cpus}).</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required final fields, enabling dependency injection of the {@link CpuService}.</li>
 * </ul>
 */
@Slf4j
@RestController
@RequestMapping("/products/cpus")
@RequiredArgsConstructor
public class CpuController {

    /** The service responsible for managing CPU products. */
    private final CpuService cpuService;

    /**
     * Creates a new CPU product.
     *
     * <p>This method accepts a {@link CPUEntity} object in the request body,
     * and delegates the creation of the new CPU product to the {@link CpuService}.
     * It returns a {@link ResponseEntity} with a {@code 201 Created} status and the created product.</p>
     *
     * @param cpuEntity the details of the CPU product to create.
     * @return a {@link ResponseEntity} containing the created {@link CPUEntity}.
     */
    @PostMapping("/create")
    public ResponseEntity<CPUEntity> createNewProduct(@RequestBody CPUEntity cpuEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cpuService.createNewProduct(cpuEntity));
    }

    /**
     * Retrieves CPU product information by product ID.
     *
     * <p>This method retrieves product information based on the product ID. It returns a {@link CompletableFuture}
     * containing a {@link ResponseEntity} with the product data if found, or a {@code 404 Not Found} status if the product
     * is not found.</p>
     *
     * @param productId the ID of the product to retrieve.
     * @return a {@link CompletableFuture} containing a {@link ResponseEntity} with the product information.
     */
    @GetMapping("/info/{productId}")
    public CompletableFuture<ResponseEntity<CpuInformationDto>> findProduct(@PathVariable Integer productId) {
        return cpuService.findProductByProductId(productId)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Updates the information of a CPU product.
     *
     * <p>This method accepts a {@link CPUEntity} object in the request body to update the CPU product with
     * the specified {@code productId}. It delegates the update process to the {@link CpuService} and
     * returns a {@link ResponseEntity} with the updated product or a {@code 404 Not Found} status if the product is not found.</p>
     *
     * @param productId the ID of the product to update.
     * @param updateProduct the product data to update.
     * @return a {@link ResponseEntity} containing the updated {@link CPUEntity}.
     */
    @PatchMapping("/update/{productId}")
    public ResponseEntity<CPUEntity> updateProductInformation(@PathVariable Integer productId, @RequestBody CPUEntity updateProduct) {
        return cpuService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deletes a CPU product by its ID.
     *
     * <p>This method deletes the CPU product with the specified {@code productId} and logs the operation.
     * It returns a {@code 204 No Content} status if the product was successfully deleted.</p>
     *
     * @param productId the ID of the product to delete.
     * @return a {@link ResponseEntity} with a {@code 204 No Content} status.
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        cpuService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}