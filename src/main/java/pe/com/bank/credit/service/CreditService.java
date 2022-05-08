package pe.com.bank.credit.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.entity.ProductEntity;
import pe.com.bank.credit.repository.CreditRepository;
import pe.com.bank.credit.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CreditService {


    private CreditRepository creditRepository;
    private ProductRepository productRepository;

    public Mono<CreditEntity> getCreditById(String id) {
        return creditRepository.findById(id);
    }

   /* public Mono<String> getProductIdByCreditId(String id){
        return creditRepository.findById(id).subscribe();
    }*/


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
                    credit.setCreditAvailable(updatedCredit.getCreditAvailable());
                    credit.setLimitCredit(updatedCredit.getLimitCredit());
                    credit.setNumberCredit(updatedCredit.getNumberCredit());
                    credit.setAmountUsed(updatedCredit.getAmountUsed());
                    credit.setType(updatedCredit.getType());
                    return creditRepository.save(credit);
                });
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
