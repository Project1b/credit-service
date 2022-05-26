package pe.com.bank.credit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.bank.credit.entity.CreditEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *Extent mongo reactive.
 */
@Repository
public interface CreditRepository extends ReactiveMongoRepository<CreditEntity,String> {
	
	Mono<Long> countByCustomerIdAndProductId(String customerId,String productId);
	Flux<CreditEntity> findByCustomerIdAndProductId(String customerId,String productId);
	Flux<CreditEntity> findByProductId(String id);
	Flux<CreditEntity> findByCustomerId(String id);


}
