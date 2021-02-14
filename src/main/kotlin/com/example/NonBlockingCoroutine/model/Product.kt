package com.example.NonBlockingCoroutine.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(name: Product, price:Float){
    @Id @GeneratedValue var id: Long? = null
    var name: String = ""
    var price: Float = 0.0f
}