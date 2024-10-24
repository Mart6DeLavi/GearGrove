package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.MotherBoardInformationDto;
import com.nznext.geargrove.products.entities.MotherBoardEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.MotherBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MotherBoardService {

    private final MotherBoardRepository motherBoardRepository;

    public MotherBoardEntity createNewProduct(MotherBoardEntity product) {
        var found = motherBoardRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return motherBoardRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<MotherBoardInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = motherBoardRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return motherBoardRepository.findProductByProductId(id)
                    .map(product -> new MotherBoardInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getFormFactor(),
                            product.getSocket(),
                            product.getChipset(),
                            product.getRamType(),
                            product.getRamCapacity(),
                            product.getFrequency(),
                            product.getSataPorts(),
                            product.getM2Ports()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + id);
                    });
        });
    }

    public Optional<MotherBoardEntity> updateProductInformation(Integer productId, MotherBoardEntity updatedProduct) {
        return motherBoardRepository.findById(productId)
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
                    return motherBoardRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = motherBoardRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            motherBoardRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
