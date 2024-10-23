package com.nznext.geargrove.products.controllers

import com.nznext.geargrove.products.dtos.FindProductByproductNameInformationDto
import com.nznext.geargrove.products.entities.CPUEntity
import com.nznext.geargrove.products.exception.NoSuchProductException
import com.nznext.geargrove.products.service.CpuServiceKotlin
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products/cpus")
class CpuControllerKotlin (private val cpuService: CpuServiceKotlin) {

    private val log = LoggerFactory.getLogger(CpuControllerKotlin::class.java)

    @PostMapping("/create")
    suspend fun createNewProduct(@RequestBody cpuEntity: CPUEntity): ResponseEntity<CPUEntity> {
        val createdProduct = cpuService.createNewProduct(cpuEntity)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }

    @GetMapping("/info/{productName}")
    suspend fun getProductInfo(@PathVariable productName: String) : ResponseEntity<FindProductByproductNameInformationDto> {
        return try {
            val productInfo = cpuService.findProductByProductName(productName)
            ResponseEntity.ok(productInfo)
        } catch (ex : NoSuchProductException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PatchMapping("/update/{productId}")
    suspend fun updateProductInformation(
        @PathVariable productId: Int,
        @RequestBody updateProduct: CPUEntity
    ) : ResponseEntity<CPUEntity> {
        return try {
            val updateProduct = cpuService.updateProductInformation(productId, updateProduct)
            ResponseEntity.ok(updateProduct)
        } catch (ex: NoSuchProductException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("/delete/{productId}")
    suspend fun deleteProduct(@PathVariable productId: Int) : ResponseEntity<Void> {
        cpuService.deleteProduct(productId)
        log.info("Product deleted: {}", productId)
        return ResponseEntity.noContent().build()
    }
}