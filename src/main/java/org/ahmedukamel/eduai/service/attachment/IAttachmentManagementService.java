package org.ahmedukamel.eduai.service.attachment;

import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.model.enumeration.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IAttachmentManagementService {
    Object saveAttachment(MultipartFile file, AttachmentType type);

    Object deleteAttachment(UUID id);

    FileResponse getAttachment(UUID id);

    Object getAttachments(int pageNumber, int pageSize);
}