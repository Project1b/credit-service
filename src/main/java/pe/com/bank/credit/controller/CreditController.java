package pe.com.bank.credit.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.exception.CreditNotFoundException;
import pe.com.bank.credit.service.CreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@AllArgsConstructor
@RestController
public class CreditController {

    CreditService creditService;

    @GetMapping("/list/credits")
    public Flux<CreditEntity> findAllCustomer() {
        return creditService.findAllCredit();
    }

    @PostMapping("/createCredits")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreditEntity> addCredit(@RequestBody CreditEntity creditEntity){
        return creditService.addCredit(creditEntity);

    }

    @DeleteMapping("/createCredits/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCredit(@PathVariable String id){
        return creditService.deleteCredit(id);

    }

    @PutMapping("/createCredits/{id}")
    public Mono<ResponseEntity<CreditEntity>> updateCredit(@RequestBody CreditEntity updatedCredit, @PathVariable String id){
        return creditService.updateCredit(updatedCredit, id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.error(new CreditNotFoundException("credit Not Found")))
                //.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                //.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).<MovieInfo>body("MovieInfo Not Found")))
                .log();

    }

}
