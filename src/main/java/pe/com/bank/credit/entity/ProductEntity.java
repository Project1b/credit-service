package pe.com.bank.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private String productId;
    private String type;
    private String productName;
    private String commission;
    private String transactionLimit;

}
