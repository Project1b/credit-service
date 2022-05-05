package pe.com.bank.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.service.CreditService;
import reactor.core.publisher.Flux;

@RestController
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping("/list/credits")
    public Flux<CreditEntity> findAllCustomer() {
        return creditService.findAllCredit();
    }
}
