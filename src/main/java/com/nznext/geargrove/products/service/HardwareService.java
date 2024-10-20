package com.nznext.geargrove.products.service;


import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.HardwareEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.HardwareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareEntity createNewProduct(HardwareEntity product) {
        var found = hardwareRepository.findProductByProductName(product.getProductName());

        if (found.isPresent()) {
            log.info("Created product: {} successfully", product.getProductName());
            return hardwareRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<FindProductByproductNameInformationDto>> findProductByProductName(String productName) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = hardwareRepository.quantityByProductName(productName);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return hardwareRepository.findProductByProductName(productName)
                    .map(product -> new FindProductByproductNameInformationDto(
                            product.getProductName(),
                            product.getPrice(),
                            product.getDescription()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + productName);
                    });
        });
    }

    public Optional<HardwareEntity> updateProductInformation(Integer productId, HardwareEntity updatedProduct) {
        return hardwareRepository.findById(productId)
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
                    return hardwareRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = hardwareRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            hardwareRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
