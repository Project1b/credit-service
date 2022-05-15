package pe.com.bank.credit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.bank.credit.entity.CreditEntity;

/**
 *Extent mongo reactive.
 */
@Repository
public interface CreditRepository extends ReactiveMongoRepository<CreditEntity, String> {
}
