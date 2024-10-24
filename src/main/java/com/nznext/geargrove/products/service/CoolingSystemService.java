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

@Slf4j
@Service
@RequiredArgsConstructor
public class CoolingSystemService {
    private final CoolingSystemRepository coolingSystemRepository;

    public CoolingSystemEntity createNewProduct(CoolingSystemEntity product) {
        var found = coolingSystemRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return coolingSystemRepository.save(product);
        } else {
            throw new NoSuchProductException("Such product already exists");
        }
    }

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
