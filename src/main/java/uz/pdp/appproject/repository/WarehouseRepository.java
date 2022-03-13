package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);
}
