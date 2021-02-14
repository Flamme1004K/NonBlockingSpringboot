package com.example.NonBlockingCoroutine.service

import com.example.NonBlockingCoroutine.model.Product
import com.example.NonBlockingCoroutine.repository.ProductRepositoryCoroutine
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl: ProductService {

    @Autowired
    lateinit var productRepository: ProductRepositoryCoroutine

    override suspend fun findById(id: Long): Product? =
        productRepository.findById(id).orElseThrow{IllegalAccessException("NotFoundId")}

    @FlowPreview
    override fun findAllProduct(): Flow<Product> = productRepository.findAll().asFlow()
}