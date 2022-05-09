package pe.com.bank.credit.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.bank.credit.client.ProductRestClient;
import pe.com.bank.credit.client.TransactionRestClient;
import pe.com.bank.credit.entity.*;
import pe.com.bank.credit.exception.CreditNotFoundException;
import pe.com.bank.credit.service.CreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class CreditController {

    CreditService creditService;
    ProductRestClient productRestClient;

    TransactionRestClient transactionRestClient;

    @GetMapping("/credits")
    public Flux<CreditEntity> findAllCustomer() {
        return creditService.findAllCredit();
    }

    @GetMapping("/credits/{id}")
    public Mono<ResponseEntity<CreditEntity>> getMovieInfoById(@PathVariable String id) {
        return creditService.getCreditById(id)
                .map(movieInfo1 -> ResponseEntity.ok()
                        .body(movieInfo1))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .log();
    }

    @PostMapping("/credits")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreditEntity> addCredit(@RequestBody CreditEntity creditEntity) {
        return creditService.addCredit(creditEntity);

    }

    @DeleteMapping("/credits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCredit(@PathVariable String id) {
        return creditService.deleteCredit(id);

    }

    @PutMapping("/credits/{id}")
    public Mono<ResponseEntity<CreditEntity>> updateCredit(@RequestBody CreditEntity updatedCredit, @PathVariable String id) {
        return creditService.updateCredit(updatedCredit, id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.error(new CreditNotFoundException("credit Not Found")))
                //.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                //.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).<MovieInfo>body("MovieInfo Not Found")))
                .log();

    }


    /// credit con product
    @GetMapping("/creditProduct/{id}")
    public Mono<CreditProduct> retrieveCreditById(@PathVariable("id") String creditId) {

        return creditService.getCreditById(creditId)
                .flatMap(movieInfo -> {
                    var reviewsListMono = productRestClient.retrieveProduct(movieInfo.getProductId());
                    return reviewsListMono.map(reviews -> new CreditProduct(
                            movieInfo.getCreditId(), movieInfo.getAmountUsed(), movieInfo.getLimitCredit(), movieInfo.getCreditAvailable(),
                            movieInfo.getNumberCredit(), movieInfo.getType(), reviews));
                });

    }

    ///credit Product Transaction;

    @GetMapping("/creditTransaction/{id}")
    public Mono<CreditTransaction> retrieveCreditAndTransactionById(@PathVariable("id") String creditId) {

        Mono<List<TransactionDTO>>  transc = transactionRestClient.retrieveProduct(creditId).collectList();
        Mono<CreditEntity> cred=creditService.getCreditById(creditId);
        Mono<CreditTransaction> credi=
                cred.flatMap(cr->
                         transc.flatMap(tr-> {
                             var  produc= productRestClient.retrieveProduct(cr.getProductId());
                             return produc.map(pr->
                                     new CreditTransaction(cr.getCreditId(),
                                             cr.getAmountUsed(),
                                             cr.getLimitCredit(),
                                             cr.getCreditAvailable(),
                                             cr.getNumberCredit(),
                                             cr.getType(),pr,tr));}));
        return credi;

     /*   return creditService.getCreditById(creditId)
                .flatMap(movieInfo -> {
                    ;
                    var transct = transactionRestClient.retrieveProduct(creditId).collectList();
                    return transct.map(review2 -> {
                        var reviewsListMono = productRestClient.retrieveProduct(movieInfo.getProductId());
                        return productRestClient.retrieveProduct(movieInfo.getProductId());.map(reviews -> new CreditTransaction(
                                movieInfo.getCreditId(),
                                movieInfo.getAmountUsed(),
                                movieInfo.getLimitCredit(),
                                movieInfo.getCreditAvailable(),
                                movieInfo.getNumberCredit(),
                                movieInfo.getType(),
                                reviews, review2));
                    });
                });*/
    }

    ;

/*
    public Mono<CreditTransaction> retrieveCreditAndTransactionById(@PathVariable("id") String creditId) {

        return creditService.getCreditById(creditId)
                .flatMap(movieInfo -> {
                    var reviewsListMono = productRestClient.retrieveProduct(movieInfo.getProductId());
                    return reviewsListMono.map(reviews -> {
                        var reviesListTransaction = transactionRestClient.retrieveProduct(creditId);
                          return reviesListTransaction.map(revies-> new CreditTransaction( movieInfo.getCreditId(), movieInfo.getAmountUsed(), movieInfo.getLimitCredit(), movieInfo.getCreditAvailable(),
                                  movieInfo.getNumberCredit(), movieInfo.getType(),reviews,revies));
                    });
                });

    }
*/


 /*   public Mono<CreditTransaction> retrieveCreditAndTransactionById(@PathVariable("id") String creditId){

        return moviesInfoRestClient.retrieveMovieInfo(movieId)
                .flatMap(movieInfo -> {
                    var reviewsListMono = reviewsRestClient.retrieveReviews(movieId)
                            .collectList();

                    return reviewsListMono.map(reviews -> new Movie(movieInfo, reviews) );
                });
    }*/

}
