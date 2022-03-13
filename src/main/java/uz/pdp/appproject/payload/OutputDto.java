package uz.pdp.appproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appproject.entity.Client;
import uz.pdp.appproject.entity.Currency;
import uz.pdp.appproject.entity.Warehouse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDto {
    private Timestamp date;
    private Integer warehouseId;

    private Integer currencyId;
    private String factureNumber;
    private String code;
    private Integer clientId;
}
