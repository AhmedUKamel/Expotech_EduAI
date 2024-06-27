package org.ahmedukamel.eduai.util.notice;

import org.ahmedukamel.eduai.model.Notice;
import org.ahmedukamel.eduai.model.NoticeDetail;
import org.springframework.context.i18n.LocaleContextHolder;

public class NoticeUtils {
    public static NoticeDetail getDetails(Notice notice) {
        return getDetails(notice, LocaleContextHolder.getLocale().getLanguage());
    }

    public static NoticeDetail getDetails(Notice notice, String languageCode) {
        return notice.getDetails()
                .stream()
                .filter(noticeDetail -> noticeDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow();
    }
}