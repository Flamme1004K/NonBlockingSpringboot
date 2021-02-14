package com.example.NonBlockingCoroutine.service

import com.example.NonBlockingCoroutine.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductService {
    suspend fun findById(id:Long):Product?
    fun findAllProduct():Flow<Product>
}