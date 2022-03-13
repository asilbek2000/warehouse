package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Input;
import uz.pdp.appproject.entity.User;

public interface InputRepository extends JpaRepository<Input,Integer> {
}
