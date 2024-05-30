package org.ahmedukamel.eduai.service.notice;

import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface INoticeManagementService {
    Object createNotice(Object object, MultipartFile file);

    Object updateNotice(Long id, Object object);

    Object uploadNoticePdf(Long id, MultipartFile file);

    Object deleteNotice(Long id);

    Object getNotice(Long id);

    Object getNotices(int pageSize, int pageNumber);

    FileResponse getNoticePdf(Long id);
}