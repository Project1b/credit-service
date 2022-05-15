package pe.com.bank.credit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.bank.credit.entity.*;
import pe.com.bank.credit.exception.CreditNotFoundException;
import pe.com.bank.credit.service.CreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class CreditController {

    CreditService creditService;

    @GetMapping("/credits")
    public Flux<CreditEntity> findAllCustomer() {
        return creditService.findAllCredit();
    }

    @GetMapping("/credits/{id}")
    public Mono<ResponseEntity<CreditEntity>> getMovieInfoById(@PathVariable String id) {
        return creditService.getCreditById(id)
                .map(credit1 -> ResponseEntity.ok()
                        .body(credit1))
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
    public Mono<ResponseEntity<CreditProduct>> retrieveCreditById(@PathVariable("id") String creditId) {
        return creditService.getCreditProduct(creditId)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.error(new CreditNotFoundException("Id no encontrado")));
    }

    @GetMapping("/creditTransaction/{id}")
    public Mono<CreditTransaction> retrieveCreditAndTransactionById(@PathVariable("id") String creditId) {
        return creditService.getCreditTransaction(creditId);
    }

}