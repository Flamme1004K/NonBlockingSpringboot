package com.example.NonBlockingCoroutine.repository

import com.example.NonBlockingCoroutine.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Long> {
}