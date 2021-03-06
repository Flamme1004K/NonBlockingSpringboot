package com.example.NonBlockingCoroutine.config

import ProductsHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun productRoutes(productsHandler: ProductsHandler) = coRouter {
        GET("/", )
        GET("/{id}", )
        GET("/{id}/stock",)
    }
}