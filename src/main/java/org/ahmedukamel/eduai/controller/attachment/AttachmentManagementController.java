package org.ahmedukamel.eduai.controller.attachment;

import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.model.enumeration.AttachmentType;
import org.ahmedukamel.eduai.service.attachment.AttachmentManagementService;
import org.ahmedukamel.eduai.service.attachment.IAttachmentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/my-attachment")
public class AttachmentManagementController {
    private final IAttachmentManagementService service;

    public AttachmentManagementController(AttachmentManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "upload")
    public ResponseEntity<?> uploadAttachment(@NotEmpty @RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "type") AttachmentType type) {
        return ResponseEntity.accepted().body(service.saveAttachment(file, type));
    }

    @DeleteMapping(value = "{attachmentId}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("attachmentId") UUID attachmentId) {
        return ResponseEntity.accepted().body(service.deleteAttachment(attachmentId));
    }

    @GetMapping(value = "download/{attachmentId}")
    public ResponseEntity<?> downloadAttachment(@PathVariable("attachmentId") UUID attachmentId) {
        FileResponse response = service.getAttachment(attachmentId);
        return ResponseEntity.accepted().headers(response.headers()).body(response.date());
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAttachments(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getAttachments(pageNumber, pageSize));
    }
}