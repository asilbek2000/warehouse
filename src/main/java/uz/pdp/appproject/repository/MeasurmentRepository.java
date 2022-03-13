package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Measurment;

public interface MeasurmentRepository extends JpaRepository<Measurment,Integer> {
    boolean existsByName(String name);
}
