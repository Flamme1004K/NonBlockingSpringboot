import com.example.NonBlockingCoroutine.controller.ProductStockView
import com.example.NonBlockingCoroutine.model.Product
import com.example.NonBlockingCoroutine.service.ProductService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.json
import org.springframework.web.servlet.function.ServerRequest
import reactor.core.publisher.Mono

@Component
class ProductsHandler(
    @Autowired var webClient: WebClient,
    @Autowired var productService: ProductService) {

    @FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyAndAwait(productService.findAllProduct())

    suspend fun findOneInStock(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()

        val product: Deferred<Product?> = GlobalScope.async {
            productService.findById(id)
        }
        val quantity: Deferred<Int> = GlobalScope.async {
            webClient.get()
                .uri("/stock-service/product/$id/quantity")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().awaitBody()
        }
        return ServerResponse.ok().json().bodyAndAwait(ProductStockView(product.await()!!, quantity.await()))
    }

    suspend fun findOne(request: ServerRequest): Mono<ServerResponse>? {
        val id = request.pathVariable("id").toLong()
        return productService.findById(id)?.let { ServerResponse.ok().json().bodyValue(it) }
    }
}


