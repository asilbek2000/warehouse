package uz.pdp.appproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appproject.entity.Output;
import uz.pdp.appproject.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductDto {
    private Integer productId;
    private Double amount;
    private Double price;
    private Date expiredate;
    private Integer outputid;
}
