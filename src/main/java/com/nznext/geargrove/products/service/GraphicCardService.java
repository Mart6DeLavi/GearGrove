package com.nznext.geargrove.products.service;

import com.nznext.geargrove.products.dtos.GraphicCardInformationDto;
import com.nznext.geargrove.products.entities.GraphicCardEntity;
import com.nznext.geargrove.products.exception.NoSuchProductException;
import com.nznext.geargrove.products.exception.ProductAlreadyExistException;
import com.nznext.geargrove.products.exception.SoldOutException;
import com.nznext.geargrove.products.repositories.GraphicCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class GraphicCardService {

    private final GraphicCardRepository graphicCardRepository;

    public GraphicCardEntity createNewProduct(GraphicCardEntity product) {
        var found = graphicCardRepository.findProductByProductName(product.getProductName());

        if (found.isEmpty()) {
            log.info("Created product: {} successfully", product.getProductName());
            return graphicCardRepository.save(product);
        } else {
            throw new ProductAlreadyExistException("Such product already exists");
        }
    }

    @Async
    public CompletableFuture<Optional<GraphicCardInformationDto>> findProductByProductId(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            var quantity = graphicCardRepository.quantityByProductId(id);
            if (quantity == 0) {
                throw new SoldOutException("This product is sold out. Sorry😢");
            }
            return graphicCardRepository.findProductByProductId(id)
                    .map(product -> new GraphicCardInformationDto(
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getQuantity(),
                            product.getSupplier(),
                            product.getYear(),
                            product.getMemory(),
                            product.getMemoryType(),
                            product.getTakeUpSlots(),
                            product.getConnectionInterface(),
                            product.getNumberOfFans(),
                            product.getNumberOfMonitors(),
                            product.getLength(),
                            product.getAdditionalPower(),
                            product.getBusBitDepth()
                    ))
                    .or(() -> {
                        throw new NoSuchProductException("No such product: " + id);
                    });
        });
    }

    public Optional<GraphicCardEntity> updateProductInformation(Integer productId, GraphicCardEntity updatedProduct) {
        return graphicCardRepository.findById(productId)
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
                    return graphicCardRepository.save(product);
                });
    }

    public void deleteProduct(Integer productId) {
        var found = graphicCardRepository.findProductByProductId(productId);

        if (found.isPresent()) {
            graphicCardRepository.deleteById(productId);
            log.info("Deleted product: {}", productId);
        } else {
            throw new NoSuchProductException("Such product does not exist");
        }
    }
}
