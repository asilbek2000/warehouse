package uz.pdp.appproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appproject.entity.Category;

import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private String name;

    private Integer parentCategoryId;
    private Boolean active;
}
