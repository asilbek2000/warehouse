package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Output;
import uz.pdp.appproject.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
}
