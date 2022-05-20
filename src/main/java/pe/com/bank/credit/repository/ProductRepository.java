package pe.com.bank.credit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.bank.credit.entity.ProductEntity;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

}
