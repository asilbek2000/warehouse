package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);
}
