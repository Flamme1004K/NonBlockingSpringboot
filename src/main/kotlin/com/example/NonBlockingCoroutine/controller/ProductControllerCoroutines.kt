package com.example.NonBlockingCoroutine.controller

import com.example.NonBlockingCoroutine.model.Product
import com.example.NonBlockingCoroutine.repository.ProductRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@RestController
class ProductControllerCoroutines {

    @Autowired
    lateinit var webClient: WebClient

    @Autowired
    lateinit var productRepository: ProductRepository

    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Long): Product? {
        return productRepository.findById(id).orElseThrow{IllegalAccessException("notFoundProductId")}
    }

    @GetMapping("/{id}/stock")
    suspend fun findOneInStock(@PathVariable id: Long): Product = coroutineScope {
        val product: Deferred<Product> = async(start = CoroutineStart.LAZY) {
            productRepository.findById(id).orElseThrow{IllegalAccessException("notFoundProductId")}
        }
        val quantity: Deferred<Float> = async(start = CoroutineStart.LAZY) {
            webClient.get()
                .uri("/stock-service/product/$id/quantity")
                .accept(APPLICATION_JSON)
                .retrieve().awaitBody<Float>()
        }
        Product(product.await().name, quantity.await())
    }

    @FlowPreview
    @GetMapping("/")
    fun findAll(): Flow<Product> {
        return productRepository.findAll().asFlow()
    }
}