package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.CpuInformationDto;
import com.nznext.geargrove.products.entities.CPUEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.CpuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service for managing CPU products in the GearGrove application.
 *
 * <p>This service provides methods to create, update, retrieve, and delete CPU products. It interacts with the
 * {@link CpuRepository} to perform database operations.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required final fields, enabling dependency injection of the {@link CpuRepository}.</li>
 * </ul>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CpuService {

    /** The repository for accessing CPU products in the database. */
    private final CpuRepository cpuRepository;

    /**
     * Creates a new CPU product.
     *
     * <p>This method checks if a product with the same name already exists. If it doesn't exist, the product is created
     * and saved to the database. If the product already exists, a {@link ProductAlreadyExistException} is thrown.</p>
     *
     * @param product the CPU product to create.
     * @return the created {@link CPUEntity}.
     * @throws ProductAlreadyExistException if a product with the same name already exists.
     */
    public CPUEntity createNewProduct(CPUEntity product) {
        var found = cpuRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return cpuRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    /**
     * Retrieves a CPU product by its product ID asynchronously.
     *
     * <p>This method uses the {@code @Async} annotation to perform the operation asynchronously. It retrieves product
     * information by ID and checks the quantity. If the product is sold out, a {@link SoldOutException} is thrown.
     * If the product exists, a {@link CpuInformationDto} is returned with the product details.</p>
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link CompletableFuture} containing the {@link CpuInformationDto}.
     * @throws SoldOutException if the product is sold out.
     * @throws NoSuchProductException if no product is found with the given ID.
     */
    @Async
    public CompletableFuture<Optional<CpuInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = cpuRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return cpuRepository.findProductByProductId(id)
                    .map(product -> new CpuInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getSocket(),
                            product.getThreads(),
                            product.getCores(),
                            product.getFrequency(),
                            product.getTechnicalProcess(),
                            product.getTDP()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product");
                    });
        });
    }

    /**
     * Updates the information of a CPU product.
     *
     * <p>This method updates the product's name, price, and quantity with the provided data. If the product is found
     * by its ID, the updated data is saved. If no product is found with the given ID, the operation returns an empty
     * {@link Optional}.</p>
     *
     * @param productId the ID of the product to update.
     * @param updatedProduct the updated product data.
     * @return an {@link Optional} containing the updated {@link CPUEntity} if successful, or {@link Optional#empty()} if not.
     */
    public Optional<CPUEntity> updateProductInformation(Integer productId, CPUEntity updatedProduct) {
        return cpuRepository.findById(productId)
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
                    return cpuRepository.save(product);
                });
    }

    /**
     * Deletes a CPU product by its ID.
     *
     * <p>This method checks if the product exists in the database. If it does, the product is deleted, and the operation
     * is logged. If no product is found, a {@link NoSuchProductException} is thrown.</p>
     *
     * @param productId the ID of the product to delete.
     * @throws NoSuchProductException if no product is found with the given ID.
     */
    public void deleteProduct(Integer productId) {
        var found = cpuRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            cpuRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}