package pe.com.bank.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credit")
public class CreditEntity {

    @Id
    private String creditId;
    private String amountUsed;
    private String limitCredit;
    private String creditAvailable;
    private String numberCredit;
    private String type;
}
