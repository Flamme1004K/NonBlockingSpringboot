package com.example.NonBlockingCoroutine.controller

import com.example.NonBlockingCoroutine.model.Product
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

class ProductStockView(product: Product, var stockQuantity: Int) : Flow<Any> {
    var id: Long? = 0
    var name: String = ""
    var price: Float = 0.0f

    init {
        this.id = product.id
        this.name = product.name
        this.price = product.price
    }

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<Any>) {
        TODO("Not yet implemented")
    }
}