package com.nznext.geargrove.products.controllers;

import com.nznext.geargrove.products.dtos.CoolingSystemInformationDto;
import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import com.nznext.geargrove.products.service.CoolingSystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * Controller for managing cooling system products in the GearGrove application.
 *
 * <p>This controller provides endpoints for creating, retrieving, updating, and deleting cooling system products.
 * It interacts with the {@link CoolingSystemService} to perform business logic related to cooling system products.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 *   <li>{@code @RestController} - Marks this class as a REST controller, where each method returns an object
 *   that will be serialized into JSON or XML (depending on the client).</li>
 *   <li>{@code @RequestMapping} - Specifies the base URL path for the endpoints in this controller ({@code /products/coolingsys}).</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required fields (final fields), enabling
 *   dependency injection of the {@link CoolingSystemService}.</li>
 * </ul>
 */
@Slf4j
@RestController
@RequestMapping("/products/coolingsys")
@RequiredArgsConstructor
public class CoolingSystemController {

    /** The service responsible for managing cooling system products. */
    private final CoolingSystemService coolingSystemService;

    /**
     * Creates a new cooling system product.
     *
     * <p>This method accepts a {@link CoolingSystemEntity} object in the request body,
     * and delegates the creation of the new product to the {@link CoolingSystemService}.
     * It returns a {@link ResponseEntity} with a {@code 201 Created} status and the created product.</p>
     *
     * @param coolingSystemEntity the details of the cooling system product to create.
     * @return a {@link ResponseEntity} with the created {@link CoolingSystemEntity}.
     */
    @PostMapping("/create")
    public ResponseEntity<CoolingSystemEntity> createNewProduct(@RequestBody CoolingSystemEntity coolingSystemEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coolingSystemService.createNewProduct(coolingSystemEntity));
    }

    /**
     * Retrieves cooling system product information by product ID.
     *
     * <p>This method retrieves product information based on the product ID. It returns a {@link CompletableFuture}
     * containing a {@link ResponseEntity} with the product data if found, or a {@code 404 Not Found} status if the product
     * is not found.</p>
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link CompletableFuture} containing a {@link ResponseEntity} with the product information.
     */
    @GetMapping("/info/{id}")
    public CompletableFuture<ResponseEntity<CoolingSystemInformationDto>> findProduct(@PathVariable Integer id) {
        return coolingSystemService.findProductByProductId(id)
                .thenApply(optionalProduct -> optionalProduct
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    /**
     * Updates the information of a cooling system product.
     *
     * <p>This method accepts a {@link CoolingSystemEntity} object in the request body to update the product with
     * the specified {@code productId}. It delegates the update process to the {@link CoolingSystemService} and
     * returns a {@link ResponseEntity} with the updated product or a {@code 404 Not Found} status if the product is not found.</p>
     *
     * @param productId the ID of the product to update.
     * @param updateProduct the product data to update.
     * @return a {@link ResponseEntity} containing the updated {@link CoolingSystemEntity}.
     */
    @PatchMapping("/update/{productId}")
    public ResponseEntity<CoolingSystemEntity> updateProductInformation(@PathVariable Integer productId, @RequestBody CoolingSystemEntity updateProduct) {
        return coolingSystemService.updateProductInformation(productId, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deletes a cooling system product by its ID.
     *
     * <p>This method deletes the cooling system product with the specified {@code productId} and logs the operation.
     * It returns a {@code 204 No Content} status if the product was successfully deleted.</p>
     *
     * @param productId the ID of the product to delete.
     * @return a {@link ResponseEntity} with a {@code 204 No Content} status.
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        coolingSystemService.deleteProduct(productId);
        log.info("Product deleted: {}", productId);
        return ResponseEntity.noContent().build();
    }
}