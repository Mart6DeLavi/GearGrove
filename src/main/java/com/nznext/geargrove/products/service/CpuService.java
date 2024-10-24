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

@Slf4j
@Service
@RequiredArgsConstructor
public class CpuService {

    private final CpuRepository cpuRepository;

    public CPUEntity createNewProduct(CPUEntity product) {
        var found = cpuRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return cpuRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

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

    public Optional<CPUEntity> updateProductInformation(Integer productId,CPUEntity updatedProduct) {
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
