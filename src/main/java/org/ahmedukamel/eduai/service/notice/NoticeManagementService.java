package org.ahmedukamel.eduai.service.notice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.notice.CreateNoticeRequest;
import org.ahmedukamel.eduai.dto.notice.NoticeResponse;
import org.ahmedukamel.eduai.dto.notice.UpdateNoticeRequest;
import org.ahmedukamel.eduai.mapper.notice.NoticeResponseMapper;
import org.ahmedukamel.eduai.model.Notice;
import org.ahmedukamel.eduai.repository.NoticeRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.saver.notice.NoticeSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.notice.NoticeUpdater;
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

@Service
@RequiredArgsConstructor
public class NoticeManagementService implements INoticeManagementService {
    private final NoticeResponseMapper noticeResponseMapper;
    private final NoticeRepository noticeRepository;
    private final NoticeUpdater noticeUpdater;
    private final NoticeSaver noticeSaver;
    private final FileSaver fileSaver;

    @Override
    public Object createNotice(Object object, MultipartFile file) {
        CreateNoticeRequest request = (CreateNoticeRequest) object;

        Notice savedNotice = noticeSaver.apply(request, file);

        NoticeResponse response = noticeResponseMapper.apply(savedNotice);
        String message = "Notice created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateNotice(Long id, Object object) {
        Notice notice = DatabaseService.get(noticeRepository::findById, id, Notice.class);
        UpdateNoticeRequest request = (UpdateNoticeRequest) object;

        Notice updatedNotice = noticeUpdater.apply(notice, request);

        NoticeResponse response = noticeResponseMapper.apply(updatedNotice);
        String message = "Notice updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object uploadNoticePdf(Long id, MultipartFile file) {
        Notice notice = DatabaseService.get(noticeRepository::findById, id, Notice.class);

        try {
            Path oldPdfPath = PathConstants.NOTICE_PDFS_PATH.resolve(notice.getPdf());

            Files.delete(oldPdfPath);

            String filename = fileSaver.save(file, PathConstants.USER_ATTACHMENTS_PATH);

            Path newPdfPath = PathConstants.NOTICE_PDFS_PATH.resolve(filename);

            Files.copy(file.getInputStream(), newPdfPath);

            notice.setPdf(filename);

        } catch (IOException exception) {
            throw new RuntimeException("Failed upload pdf.", exception);
        }

        Notice updatedNotice = noticeRepository.save(notice);

        NoticeResponse response = noticeResponseMapper.apply(updatedNotice);
        String message = "Notice updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteNotice(Long id) {
        Notice notice = DatabaseService.get(noticeRepository::findById, id, Notice.class);

        try {
            Files.delete(PathConstants.NOTICE_PDFS_PATH.resolve(notice.getPdf()));
        } catch (IOException exception) {
            throw new RuntimeException("Failed delete pdf.", exception);
        }

        noticeRepository.delete(notice);

        String message = "Notice deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getNotice(Long id) {
        Notice notice = DatabaseService.get(noticeRepository::findById, id, Notice.class);

        NoticeResponse response = noticeResponseMapper.apply(notice);
        String message = "Notice retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getNotices(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<Notice> notices = noticeRepository.findAll(pageable);

        Page<NoticeResponse> response = notices.map(noticeResponseMapper);
        String message = "Notices retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public FileResponse getNoticePdf(Long id) {
        Notice notice = DatabaseService.get(noticeRepository::findById, id, Notice.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().build());

        try {
            byte[] data = Files.readAllBytes(PathConstants.NOTICE_PDFS_PATH.resolve(notice.getPdf()));

            return new FileResponse(data, headers);
        } catch (IOException exception) {
            throw new RuntimeException("Failed get pdf.", exception);
        }
    }
}