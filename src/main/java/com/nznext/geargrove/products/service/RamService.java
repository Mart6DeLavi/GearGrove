package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto;
import com.nznext.geargrove.products.entities.RamEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.RamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class RamService {

    private final RamRepository ramRepository;


    public RamEntity createNewProduct(RamEntity product) {
        var found = ramRepository.findProductByProductName(product.getProductName());

        if (found.isPresent()) {
            log.info("Created product: {} successfully", product.getProductName());
            return ramRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<FindProductByproductNameInformationDto>> findProductByProductName(String productName) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = ramRepository.quantityByProductName(productName);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return ramRepository.findProductByProductName(productName)
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

    public Optional<RamEntity> updateProductInformation(Integer productId, RamEntity updatedProduct) {
        return ramRepository.findById(productId)
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
                    return ramRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = ramRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            ramRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
