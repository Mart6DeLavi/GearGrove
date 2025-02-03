package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.CoolingSystemInformationDto;
import com.nznext.geargrove.products.entities.CoolingSystemEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.CoolingSystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service for managing cooling system products in the GearGrove application.
 *
 * <p>This service provides methods to create, update, retrieve, and delete cooling system products.
 * It interacts with the {@link CoolingSystemRepository} to perform data access operations.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required final fields, enabling
 *   dependency injection of the {@link CoolingSystemRepository}.</li>
 * </ul>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoolingSystemService {

    /** The repository for accessing cooling system products in the database. */
    private final CoolingSystemRepository coolingSystemRepository;

    /**
     * Creates a new cooling system product.
     *
     * <p>This method checks if a product with the same name already exists. If it doesn't exist, the product is created
     * and saved to the database. If the product already exists, a {@link NoSuchProductException} is thrown.</p>
     *
     * @param product the cooling system product to create.
     * @return the created {@link CoolingSystemEntity}.
     * @throws NoSuchProductException if a product with the same name already exists.
     */
    public CoolingSystemEntity createNewProduct(CoolingSystemEntity product) {
        var found = coolingSystemRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return coolingSystemRepository.save(product);
        } else {
            throw new NoSuchProductException("Such product already exists");
        }
    }

    /**
     * Retrieves a cooling system product by its ID asynchronously.
     *
     * <p>This method uses the {@code @Async} annotation to perform the operation asynchronously. It retrieves product
     * information by ID and checks the quantity. If the product is sold out, a {@link SoldOutException} is thrown.
     * If the product exists, a {@link CoolingSystemInformationDto} is returned with the product details.</p>
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link CompletableFuture} containing the {@link CoolingSystemInformationDto}.
     * @throws SoldOutException if the product is sold out.
     * @throws NoSuchProductException if no product is found with the given ID.
     */
    @Async
    public CompletableFuture<Optional<CoolingSystemInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = coolingSystemRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return coolingSystemRepository.findProductByProductId(id)
                    .map(product -> new CoolingSystemInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getPurpose(),
                            product.getType(),
                            product.getFanDiameter()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product");
                    });
        });
    }

    /**
     * Updates the information of a cooling system product.
     *
     * <p>This method updates the product's name, price, and quantity with the provided data. If the product is found
     * by its ID, the updated data is saved. If no product is found with the given ID, the operation returns an empty
     * {@link Optional}.</p>
     *
     * @param productId the ID of the product to update.
     * @param updatedProduct the updated product data.
     * @return an {@link Optional} containing the updated {@link CoolingSystemEntity} if successful, or {@link Optional#empty()} if not.
     */
    public Optional<CoolingSystemEntity> updateProductInformation(Integer productId, CoolingSystemEntity updatedProduct) {
        return coolingSystemRepository.findById(productId)
                .map(product -> {
                    if (updatedProduct.getProductName() != null) {
                        product.setProductName(updatedProduct.getProductName());
                    }
                    if (updatedProduct.getPrice() != null) {
                        product.setPrice(updatedProduct.getPrice());
                    }
                    if (updatedProduct.getQuantity() != null) {
                        product.setQuantity(updatedProduct.getQuantity());
                    }
                    return coolingSystemRepository.save(product);
                });
    }

    /**
     * Deletes a cooling system product by its ID.
     *
     * <p>This method checks if the product exists in the database. If it does, the product is deleted, and the operation
     * is logged. If no product is found, a {@link NoSuchProductException} is thrown.</p>
     *
     * @param productId the ID of the product to delete.
     * @throws NoSuchProductException if no product is found with the given ID.
     */
    public void deleteProduct(Integer productId) {
        var found = coolingSystemRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            coolingSystemRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}