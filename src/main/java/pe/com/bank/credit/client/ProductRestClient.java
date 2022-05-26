package pe.com.bank.credit.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.bank.credit.entity.ProductEntity;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductRestClient {

    private WebClient webClient;

    @Value("${restClient.productUrl}")
    private String productUrl;

    public ProductRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductEntity> retrieveProduct(String productId){

        var url = productUrl.concat("/getProduct/{id}");
        return webClient
                .get()
                .uri(url, productId)
                .retrieve()
                .bodyToMono(ProductEntity.class)
                .log();
    }

}