package org.ahmedukamel.eduai.updater.notice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.notice.UpdateNoticeRequest;
import org.ahmedukamel.eduai.model.Notice;
import org.ahmedukamel.eduai.model.NoticeDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.NoticeRepository;
import org.ahmedukamel.eduai.util.notice.NoticeUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class NoticeUpdater implements BiFunction<Notice, UpdateNoticeRequest, Notice> {
    private final NoticeRepository noticeRepository;

    @Override
    public Notice apply(Notice notice, UpdateNoticeRequest request) {
        NoticeDetail noticeDetail_en = NoticeUtils.getDetails(notice, Language.ENGLISH.getCode()),
                noticeDetail_ar = NoticeUtils.getDetails(notice, Language.ARABIC.getCode()),
                noticeDetail_fr = NoticeUtils.getDetails(notice, Language.FRENCH.getCode());

        noticeDetail_en.setTitle(request.title_en().strip());
        noticeDetail_en.setDescription(request.description_en().strip());

        noticeDetail_ar.setTitle(request.title_ar().strip());
        noticeDetail_ar.setDescription(request.description_ar().strip());

        noticeDetail_fr.setTitle(request.title_fr().strip());
        noticeDetail_fr.setDescription(request.description_fr().strip());

        notice.setActive(request.active());

        return noticeRepository.save(notice);
    }
}