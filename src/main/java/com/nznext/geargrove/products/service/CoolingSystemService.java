package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CoolingSystemService {

    private final CoolingSystemRepository coolingSystemRepository;

    public CoolingSystemEntity createNewProduct(CoolingSystemEntity coolingSystemEntity) {
        log.info("Created new product: {}, \n{}, \n{}", coolingSystemEntity.getId(), coolingSystemEntity.getProductName(), coolingSystemEntity.getQuantity());
        return coolingSystemRepository.save(coolingSystemEntity);
    }

    @Async
    public CompletableFuture<Optional<FindProductByproductNameInformationDto>> findProductByProductName(String productName) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = coolingSystemRepository.quantityByProductName(productName);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return coolingSystemRepository.findProductByProductName(productName)
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

    public Optional<CoolingSystemEntity> updateProductInformation(Long productId, CoolingSystemEntity updatedProduct) {
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

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        log.info("Deleted product: {}", productId);
    }
}
