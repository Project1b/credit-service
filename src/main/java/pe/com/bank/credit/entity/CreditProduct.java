package pe.com.bank.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditProduct {

    private String creditId;
    private Double amountUsed;
    private Double limitCredit;
    private Double creditAvailable;
    private String numberCredit;
    private String type;
    private ProductEntity productEntity;

}
