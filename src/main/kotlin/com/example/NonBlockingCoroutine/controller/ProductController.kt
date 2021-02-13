package com.example.NonBlockingCoroutine.controller
import com.example.NonBlockingCoroutine.model.Product
import com.example.NonBlockingCoroutine.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProductController {
    @Autowired
    lateinit var webClient: WebClient
    @Autowired
    lateinit var productRepository: ProductRepository

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Mono<Product> {
        return productRepository.findById(id)
    }

    @GetMapping("/{id}/stock")
    fun findOneInStock(@PathVariable id: Long): Mono<Product> {
        val product = productRepository.findById(id).orElseThrow { IllegalAccessException("notFoundProductId") }

        val stockQuantity = webClient.get()
            .uri("/stock-service/product/$id/quantity")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono<Int>()
        return product.zipWith(stockQuantity) { productInStock, stockQty ->
            ProductStockView(productInStock, stockQty)
        }
    }

    @GetMapping("/stock-service/product/{id}/quantity")
    fun getStockQuantity(): Mono<Int> {
        return Mono.just(2)
    }

    @GetMapping("/")
    fun findAll(): Flux<Product> {
        return productRepository.getAllProducts()
    }
}