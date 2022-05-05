package pe.com.bank.credit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.bank.credit.entity.CreditEntity;
import pe.com.bank.credit.repository.CreditRepository;
import reactor.core.publisher.Flux;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;

    public Flux<CreditEntity> findAllCredit(){
        return creditRepository.findAll();
    }

}
