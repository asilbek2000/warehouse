package uz.pdp.appproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean active=true;
    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment photo;
    @ManyToOne
    private Measurment measurment;
}
