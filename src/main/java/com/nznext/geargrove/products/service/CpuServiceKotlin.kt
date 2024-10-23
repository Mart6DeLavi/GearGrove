package com.nznext.geargrove.products.service

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto
import com.nznext.geargrove.products.entities.CPUEntity
import com.nznext.geargrove.products.exception.NoSuchProductException
import com.nznext.geargrove.products.exception.ProductAlreadyExistException
import com.nznext.geargrove.products.exception.SoldOutException
import com.nznext.geargrove.products.repositories.CpuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CpuServiceKotlin(private val cpuRepository: CpuRepository) {

    private val log = LoggerFactory.getLogger(CpuServiceKotlin::class.java)

    suspend fun createNewProduct(product: CPUEntity): CPUEntity {
        val found = cpuRepository.findProductByProductName(product.productName)

        if (found.isPresent) {
            log.info("Created product: {} successfully", product.productName)
            return cpuRepository.save(product)
        } else {
            throw ProductAlreadyExistException("Such product already exist")
        }
    }

    suspend fun findProductByProductName(productName: String): FindProductByproductNameInformationDto {
        return withContext(Dispatchers.IO) {
            val quantity = cpuRepository.quantityByProductName(productName)
            if (quantity == 0) {
                throw SoldOutException("This product is sold out. Sorry 😢")
            }
            cpuRepository.findProductByProductName(productName)
                .map { product ->
                    FindProductByproductNameInformationDto(
                        product.productName,
                        product.frequency,
                        product.description
                    )
                }
                .orElseThrow { NoSuchProductException("No such product: $productName") }
        }
    }

    suspend fun updateProductInformation(productId: Int, updateProduct: CPUEntity) : CPUEntity {
        return withContext(Dispatchers.IO) {
            val product = cpuRepository.findProductByProductId(productId)
                .orElseThrow { NoSuchProductException("No product with such id: $productId") }
            updateProduct.productName?.let { product.productName  = it }
            updateProduct.price?.let { product.price = it }
            updateProduct.quantity?.let {product.quantity = it}

            cpuRepository.save(product)
        }
    }

    suspend fun deleteProduct(productId: Int) {
        withContext(Dispatchers.IO) {
            val found = cpuRepository.findProductByProductId(productId)

            if (found.isPresent) {
                cpuRepository.deleteById(productId)
                log.info("Deleted product: {}", productId)
            } else {
                throw NoSuchProductException("No product with such id: $productId")
            }
        }
    }
}