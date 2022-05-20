package pe.com.bank.credit.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.bank.credit.entity.TransactionDTO;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class TransactionRestClient {

    private WebClient webClient;

    @Value("${restClient.transactionUrl}")
    private String transactionUrl;

    public TransactionRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<TransactionDTO> retrieveProduct(String productId){

        var url = transactionUrl.concat("/credit/{id}");
        return webClient
                .get()
                .uri(url, productId)
                .retrieve()
                .bodyToFlux(TransactionDTO.class);
    }
}