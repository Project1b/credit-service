package pe.com.bank.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditProduct {

    private String creditId;
    private String amountUsed;
    private String limitCredit;
    private String creditAvailable;
    private String numberCredit;
    private String type;
    private ProductEntity productEntity;

}
