package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.InputProduct;
import uz.pdp.appproject.entity.User;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
