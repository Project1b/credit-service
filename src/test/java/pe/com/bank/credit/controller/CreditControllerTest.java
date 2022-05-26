package pe.com.bank.credit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.bank.credit.entity.CreditEntity;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private CreditEntity creditEntity;

    @Test
    void findAllCredit() {
        this.webTestClient
                .get()
                .uri("/v1/credits")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CreditEntity.class);
    }

    @Test
    void getCredit(){
        this.webTestClient
                .get()
                .uri("/v1/credits/627422eb432d34d63b6920a0")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreditEntity.class);
    }

    @Test
    void addCredit(){
        creditEntity=new CreditEntity();
        creditEntity.setNumberCredit("44455566677788");
        creditEntity.setProductId("6275a7aab557542205eb1c1d");
        creditEntity.setCreditAvailable(1000.0);
        creditEntity.setCustomerId("62833f4d9a36154aa2e6e25c");
        creditEntity.setAmountUsed(400.0);
        this.webTestClient
                .post()
                .uri("/v1/credits")
                .body(Mono.just(creditEntity),CreditEntity.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreditEntity.class);
    }

    @Test
    void deleteCredit(){
        this.webTestClient
                .delete()
                .uri("/v1/credits/628f11944c0e0409f40670ab")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void updateCredit(){
        creditEntity = new CreditEntity();
        creditEntity.setAmountUsed(450.0);
        creditEntity.setCreditAvailable(990.0);
        this.webTestClient
                .put()
                .uri("/v1/credits/628f1130545d9565d9e55439")
                .body(Mono.just(creditEntity),CreditEntity.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreditEntity.class);
    }


}
