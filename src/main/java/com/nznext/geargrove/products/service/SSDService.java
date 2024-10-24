package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.SSDInformationDto;
import com.nznext.geargrove.products.entities.SSDEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.SSDRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SSDService {

    private final SSDRepository ssdRepository;

    public SSDEntity createNewProduct(SSDEntity product) {
        var found = ssdRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return ssdRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<SSDInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = ssdRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return ssdRepository.findProductByProductId(id)
                    .map(product -> new SSDInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getType(),
                            product.getStorageCapacity(),
                            product.getAnInterface(),
                            product.getMemoryType()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + id);
                    });
        });
    }

    public Optional<SSDEntity> updateProductInformation(Integer productId, SSDEntity updatedProduct) {
        return ssdRepository.findById(productId)
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
                    return ssdRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = ssdRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            ssdRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
