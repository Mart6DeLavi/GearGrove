package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.PowerUnitInformationDto;
import com.nznext.geargrove.products.entities.PowerUnitEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.PowerUnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class PowerUnitService {

    private final PowerUnitRepository powerUnitRepository;

    public PowerUnitEntity createNewProduct(PowerUnitEntity product) {
        var found = powerUnitRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return powerUnitRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<PowerUnitInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = powerUnitRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return powerUnitRepository.findProductByProductId(id)
                    .map(product -> new PowerUnitInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getPower(),
                            product.getFormFactors(),
                            product.getCertificate(),
                            product.getCabellSystem(),
                            product.getFanDiameter(),
                            product.getWarranty()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + id);
                    });
        });
    }

    public Optional<PowerUnitEntity> updateProductInformation(Integer productId, PowerUnitEntity updatedProduct) {
        return powerUnitRepository.findById(productId)
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
                    return powerUnitRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = powerUnitRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            powerUnitRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
