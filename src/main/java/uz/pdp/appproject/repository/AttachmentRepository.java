package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.Attachment;
import uz.pdp.appproject.entity.User;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
