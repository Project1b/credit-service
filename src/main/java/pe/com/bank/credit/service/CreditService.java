package pe.com.bank.credit.service;

import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.entity.CreditProduct;
import pe.com.bank.credit.entity.CreditTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    public Mono<CreditEntity> getCreditById(String id);

    public Flux<CreditEntity> findAllCredit();

    public Mono<CreditEntity> addCredit(CreditEntity creditEntity);

    public Mono<Void> deleteCredit(String id);

    public Mono<CreditEntity> updateCredit(CreditEntity updatedCredit, String id);

    public Mono<CreditProduct> getCreditProduct(String creditId);

    public Mono<CreditTransaction> getCreditTransaction(String creditId);

    public Mono<Long>getCountByCustomerIdAndProductId(String customerId,String productId);

    public Flux<CreditEntity> getByProductId(String id);

    public Flux<CreditEntity>getByCustomerId(String id);
    
    public Flux<CreditEntity> getByCustomerAndProductId(String customerId,String productId);


}
