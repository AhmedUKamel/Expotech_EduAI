package org.ahmedukamel.eduai.mapper.notice;

import org.ahmedukamel.eduai.constant.ApiConstants;
import org.ahmedukamel.eduai.dto.notice.NoticeResponse;
import org.ahmedukamel.eduai.model.Notice;
import org.ahmedukamel.eduai.model.NoticeDetail;
import org.ahmedukamel.eduai.util.notice.NoticeUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class NoticeResponseMapper implements Function<Notice, NoticeResponse> {
    @Override
    public NoticeResponse apply(Notice notice) {
        NoticeDetail detail = NoticeUtils.getDetails(notice);

        return new NoticeResponse(
                notice.getId(),
                notice.getUser().getId(),
                notice.getSchool().getId(),
                notice.isActive(),
                notice.getCreationDate(),
                notice.getUpdateDate(),
                detail.getTitle(),
                detail.getDescription(),
                ApiConstants.NOTICE_PDF_API_URL.formatted(notice.getId())
        );
    }
}