package pe.com.bank.credit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.com.bank.credit.entity.CreditEntity;
import reactor.test.StepVerifier;

@SpringBootTest
class CreditServiceTest {


    @Autowired
    CreditService creditService;

    CreditEntity creditEntity;


    @Test
    void getCreditId(){
        creditEntity = new CreditEntity();
        creditEntity.setCreditId("628f1130545d9565d9e55439");
        creditEntity.setAmountUsed(450.0);
        creditEntity.setCreditAvailable(990.0);
        creditEntity.setNumberCredit("44455566677788");
        creditEntity.setProductId("6275a7aab557542205eb1c1d");
        creditEntity.setCustomerId("62833f4d9a36154aa2e6e25c");
        StepVerifier.create(creditService.getCreditById("628f1130545d9565d9e55439"))
                .expectNext(creditEntity)
                .expectComplete()
                .verify();
    }

    @Test
    void createCred(){

        creditEntity=new CreditEntity();
        creditEntity.setAmountUsed(990.0);
        creditEntity.setLimitCredit(8990.0);
        creditEntity.setCreditAvailable(1900.0);
        creditEntity.setNumberCredit("12312399");
        creditEntity.setType("tarjeta");
        creditEntity.setProductId("627565638e1580ec83b69c7d");
        creditEntity.setCustomerId("6275a7aab55754220c199");
        StepVerifier.create(creditService.addCredit(creditEntity))
                .expectNext(creditEntity)
                .expectComplete()
                .verify();
    }

    @Test
    void deleteCred(){
        StepVerifier.create(creditService.deleteCredit("628f1f0130392c033b99451a"))
                .verifyComplete();
    }

}
