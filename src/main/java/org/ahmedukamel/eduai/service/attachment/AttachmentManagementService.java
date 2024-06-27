package org.ahmedukamel.eduai.service.attachment;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.model.Attachment;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.AttachmentType;
import org.ahmedukamel.eduai.repository.AttachmentRepository;
import org.ahmedukamel.eduai.saver.attachment.AttachmentSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentManagementService implements IAttachmentManagementService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentSaver attachmentSaver;

    @Override
    public Object saveAttachment(MultipartFile file, AttachmentType type) {
        Attachment attachment = attachmentSaver.apply(file, type);

        try {
            String filename = "%s.%s".formatted(attachment.getId(), attachment.getExtension());
            Path filepath = PathConstants.USER_ATTACHMENTS_PATH.resolve(filename);

            if (!Files.exists(PathConstants.USER_ATTACHMENTS_PATH)) {
                Files.createDirectories(PathConstants.USER_ATTACHMENTS_PATH);
            }

            Files.copy(file.getInputStream(), filepath);


        } catch (IOException exception) {
            attachmentRepository.delete(attachment);

            throw new RuntimeException("Couldn't save attachment.", exception);
        }

        String message = "Attachment saved successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object deleteAttachment(UUID id) {
        Attachment attachment = getUserAttachment(id);

        try {
            String filename = "%s.%s".formatted(attachment.getId(), attachment.getExtension());
            Files.delete(PathConstants.USER_ATTACHMENTS_PATH.resolve(filename));

            attachmentRepository.delete(attachment);
            String message = "Attachment deleted successfully.";

            return new ApiResponse(true, message, null);

        } catch (IOException exception) {
            throw new RuntimeException("Couldn't delete attachment.", exception);
        }
    }

    @Override
    public FileResponse getAttachment(UUID id) {
        Attachment attachment = getUserAttachment(id);

        String filename = "%s.%s".formatted(attachment.getId(), attachment.getExtension());
        Path filepath = PathConstants.USER_ATTACHMENTS_PATH.resolve(filename);

        final byte[] date;
        try {
            date = Files.readAllBytes(filepath);
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't read attachment.", exception);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

        return new FileResponse(date, headers);
    }

    @Override
    public Object getAttachments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        User user = ContextHolderUtils.getUser();

        Page<Attachment> response = attachmentRepository.findAllByUser(user, pageable);
        String message = "Attachment list retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    private Attachment getUserAttachment(UUID id) {
        Attachment attachment = DatabaseService.get(attachmentRepository::findById, id, Attachment.class);

        if (!attachment.getUser().getId().equals(ContextHolderUtils.getUser().getId())) {
            throw new RuntimeException("You do not have permission to delete this attachment.");
        }
        return attachment;
    }
}