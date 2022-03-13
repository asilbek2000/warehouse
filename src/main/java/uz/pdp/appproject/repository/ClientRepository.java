package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Client;
import uz.pdp.appproject.entity.User;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
