package uz.pdp.appproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproject.entity.AttachmentContent;
import uz.pdp.appproject.entity.User;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    Optional<AttachmentContent> findByAttachmentId(Integer id);

}
