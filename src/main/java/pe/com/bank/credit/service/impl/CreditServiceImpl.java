package pe.com.bank.credit.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.bank.credit.client.ProductRestClient;
import pe.com.bank.credit.client.TransactionRestClient;
import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.entity.CreditProduct;
import pe.com.bank.credit.entity.CreditTransaction;
import pe.com.bank.credit.entity.TransactionDTO;
import pe.com.bank.credit.repository.CreditRepository;
import pe.com.bank.credit.repository.ProductRepository;
import pe.com.bank.credit.service.CreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    CreditRepository creditRepository;

    ProductRepository productRepository;
    ProductRestClient productRestClient;
    TransactionRestClient transactionRestClient;

    public Mono<CreditEntity> getCreditById(String id) {
        return creditRepository.findById(id);
    }

    public Flux<CreditEntity> findAllCredit() {
        return creditRepository.findAll();
    }

    public Mono<CreditEntity> addCredit(CreditEntity creditEntity) {
        return creditRepository.save(creditEntity);
    }

    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }

    public Mono<CreditEntity> updateCredit(CreditEntity updatedCredit, String id) {

        return creditRepository.findById(id)
                .flatMap(credit -> {
                    credit.setCreditAvailable(updatedCredit.getCreditAvailable() != null ? updatedCredit.getCreditAvailable() : credit.getCreditAvailable());
                    credit.setLimitCredit(updatedCredit.getLimitCredit() != null ? updatedCredit.getLimitCredit() : credit.getLimitCredit());
                    credit.setNumberCredit(updatedCredit.getNumberCredit() != null ? updatedCredit.getNumberCredit() : credit.getNumberCredit());
                    credit.setAmountUsed(updatedCredit.getAmountUsed() != null ? updatedCredit.getAmountUsed() : credit.getAmountUsed());
                    credit.setType(updatedCredit.getType() != null ? updatedCredit.getType() : credit.getType());
                    credit.setProductId(updatedCredit.getProductId() != null ? updatedCredit.getProductId() : credit.getProductId());
                    return creditRepository.save(credit);
                });
    }

    public Mono<CreditProduct> getCreditProduct(String creditId) {
        return getCreditById(creditId)
                .flatMap(credit12 -> productRestClient.retrieveProduct(credit12.getProductId())
                        .map(product2 -> new CreditProduct(
                                credit12.getCreditId(), credit12.getAmountUsed(), credit12.getLimitCredit(), credit12.getCreditAvailable(),
                                credit12.getNumberCredit(), credit12.getType(), product2)));
    }

    public Mono<CreditTransaction> getCreditTransaction(String creditId) {
        return getCreditById(creditId)
                .flatMap(cr -> transactionRestClient.retrieveProduct(creditId).collectList()
                        .flatMap(tr -> productRestClient.retrieveProduct(cr.getProductId())
                                .map(pr -> new CreditTransaction(cr.getCreditId(),
                                        cr.getAmountUsed(), cr.getLimitCredit(),
                                        cr.getCreditAvailable(), cr.getNumberCredit(),
                                        cr.getType(), pr, tr))
                        ));
    }


    public Mono<Long> getCountByCustomerIdAndProductId(String customerId,String productId){
        return creditRepository.countByCustomerIdAndProductId(customerId, productId);
    }


    public Flux<CreditEntity> getByProductId(String id){
        return creditRepository.findByProductId(id);
    }

    public Flux<CreditEntity> getByCustomerId(String id){
        return creditRepository.findByCustomerId(id);
    }

    public Flux<CreditEntity> getByCustomerAndProductId(String customerId,String productId){
    	return creditRepository.findByCustomerIdAndProductId(customerId, productId);
    }
    
  /*  public Flux<ProductEntity> findProductByCreditId(String id) {
        return creditRepository
                .findById(id)
                .thenMany(productRepository.findAll())
                .filter(comment1 -> comment1.getIdCredit()
                        .equals(id));

    }*/

/*    public Mono<CreditEntity> findPostByIdShowComments(String id) {
        return creditRepository
                .findById(id)
                .flatMap(postFound -> ProductService
                        .findCommentsByPostId(postFound.getId())
                        .collectList()
                        .flatMap(comments -> {
                            postFound.setListComments(comments);
                            return Mono.just(postFound);
                        })
                );
    }*/





}
