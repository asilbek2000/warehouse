package uz.pdp.appproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {
    private String name;
    private Boolean active=true;
    private Integer CategoryId;
    private Integer photoId;
    private Integer measurmentid;
}
