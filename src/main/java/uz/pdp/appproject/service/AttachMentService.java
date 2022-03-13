package uz.pdp.appproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appproject.entity.Attachment;
import uz.pdp.appproject.entity.AttachmentContent;
import uz.pdp.appproject.repository.AttachmentContentRepository;
import uz.pdp.appproject.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachMentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        while(fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());

            if (file != null) {
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
                Attachment attachment = new Attachment();
                attachment.setFilesize(size);
                attachment.setContentType(contentType);
                attachment.setName(originalFilename);
                Attachment savedAttachment = attachmentRepository.save(attachment);
                AttachmentContent attachMentContent = new AttachmentContent();
                attachMentContent.setAttachment(savedAttachment);
                attachMentContent.setBytes(file.getBytes());
                attachmentContentRepository.save(attachMentContent);

            }
        }
        return "All files added successfully";
    }
    public void getFilesInfo( Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        Attachment attachment = byId.get();
        Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
        if (byAttachmentId.isPresent()) {
            AttachmentContent attachMentContent = byAttachmentId.get();

            response.setHeader("Content-Disposition","attachment; filename=\"" +
                    attachment.getName()+"\"");

            response.setContentType(attachment.getContentType());
            FileCopyUtils.copy(attachMentContent.getBytes(),response.getOutputStream());
        }
    }
}
