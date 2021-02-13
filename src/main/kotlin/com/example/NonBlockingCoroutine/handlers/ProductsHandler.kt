import com.example.NonBlockingCoroutine.repository.ProductRepository
import kotlinx.coroutines.FlowPreview
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.servlet.function.ServerRequest

@Component
class ProductsHandler(
    @Autowired var webClient: WebClient,
    @Autowired var productRepository: ProductRepository) {

    @FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse? = null


    suspend fun findOneInStock(request: ServerRequest): ServerResponse? =null

    suspend fun findOne(request: ServerRequest): ServerResponse? =null
}
