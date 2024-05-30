package org.ahmedukamel.eduai.dto.notice;

import java.time.LocalDateTime;

public record NoticeResponse(
        Long id,

        Long userId,

        Integer schoolId,

        boolean active,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        String title,

        String description,

        String pdfUrl
) {
}