package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.Product;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.SoldOutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService<T extends Product> {

    private final JpaRepository<T, Integer> productRepository;

    public T createNewProduct(T product) {
        log.info("Created product: {} successfully", product.getProductName());
        return productRepository.save(product);
    }

    @Async
    public CompletableFuture<Optional<FindProductByproductNameInformationDto>> findProductByProductName(String productName) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = productRepository.quantityByProductName(productName);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return productRepository.findProductByProductName(productName)
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

    public Optional<T> updateProductInformation(Integer productId, T updatedProduct) {
        return productRepository.findById(productId)
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
                    return productRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        log.info("Deleted product: {}", productId);
    }
}

