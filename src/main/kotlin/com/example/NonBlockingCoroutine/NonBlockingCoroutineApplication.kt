package com.example.NonBlockingCoroutine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NonBlockingCoroutineApplication

fun main(args: Array<String>) {
	runApplication<NonBlockingCoroutineApplication>(*args)
}
