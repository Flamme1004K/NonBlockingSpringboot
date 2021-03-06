package com.example.NonBlockingCoroutine.repository

import com.example.NonBlockingCoroutine.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

//ReactiveCrudRepository는 아직 jpa에서 지원하지 않는다.

@Repository
interface ProductRepository: CrudRepository<Product, Long> {
}