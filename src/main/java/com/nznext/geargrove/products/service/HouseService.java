package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.HouseInformationDto;
import com.nznext.geargrove.products.entities.HouseEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    public HouseEntity createNewProduct(HouseEntity product) {
        var found = houseRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return houseRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<HouseInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = houseRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return houseRepository.findProductByProductId(id)
                    .map(product -> new HouseInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getFormFactor(),
                            product.getMotherBoardType(),
                            product.getHousingPlacement(),
                            product.isPowerUnit(),
                            product.getAdditionalFunctions(),
                            product.getDustFilter()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + id);
                    });
        });
    }

    public Optional<HouseEntity> updateProductInformation(Integer productId, HouseEntity updatedProduct) {
        return houseRepository.findById(productId)
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
                    return houseRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = houseRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            houseRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
