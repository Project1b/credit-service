package pe.com.bank.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditTransaction {

    private String creditId;
    private Double amountUsed;
    private Double limitCredit;
    private Double creditAvailable;
    private String numberCredit;
    private String type;
    private ProductEntity productEntity;
    private List<TransactionDTO> transactionDTOList;

}