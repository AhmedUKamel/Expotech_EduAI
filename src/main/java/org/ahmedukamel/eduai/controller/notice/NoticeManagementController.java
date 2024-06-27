package org.ahmedukamel.eduai.controller.notice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.notice.CreateNoticeRequest;
import org.ahmedukamel.eduai.dto.notice.UpdateNoticeRequest;
import org.ahmedukamel.eduai.service.notice.INoticeManagementService;
import org.ahmedukamel.eduai.service.notice.NoticeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Validated
@RestController
@RequestMapping(value = "api/v1/notice")
public class NoticeManagementController {
    private final INoticeManagementService service;

    public NoticeManagementController(NoticeManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createNotice(@Valid @RequestParam(value = "notice") CreateNoticeRequest request,
                                          @NotEmpty @RequestParam(value = "pdf") MultipartFile pdf) {
        return ResponseEntity.created(URI.create("api/v1/notice"))
                .body(service.createNotice(request, pdf));
    }

    @PutMapping(value = "{noticeId}")
    public ResponseEntity<?> updateNotice(@Min(value = 1) @PathVariable(value = "noticeId") Long id,
                                          @Valid @RequestBody UpdateNoticeRequest request) {
        return ResponseEntity.accepted().body(service.updateNotice(id, request));
    }

    @PutMapping(value = "{noticeId}/upload")
    public ResponseEntity<?> uploadNoticePdf(@Min(value = 1) @PathVariable(value = "noticeId") Long id,
                                             @NotEmpty @RequestParam(value = "pdf") MultipartFile pdf) {
        return ResponseEntity.accepted().body(service.uploadNoticePdf(id, pdf));
    }

    @DeleteMapping(value = "{noticeId}")
    public ResponseEntity<?> uploadNoticePdf(@Min(value = 1) @PathVariable(value = "noticeId") Long id) {
        return ResponseEntity.accepted().body(service.deleteNotice(id));
    }

    @GetMapping(value = "{noticeId}")
    public ResponseEntity<?> getNotice(@Min(value = 1) @PathVariable(value = "noticeId") Long id) {
        return ResponseEntity.ok().body(service.getNotice(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getNotices(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                        @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getNotices(pageSize, pageNumber));
    }

    @GetMapping(value = "public/{noticeId}/pdf")
    public ResponseEntity<?> getNoticePdf(@Min(value = 1) @PathVariable(value = "noticeId") Long id) {
        FileResponse response = service.getNoticePdf(id);
        return ResponseEntity.ok().headers(response.headers()).body(response.date());
    }
}