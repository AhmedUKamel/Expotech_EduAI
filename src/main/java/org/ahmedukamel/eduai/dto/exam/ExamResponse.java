package org.ahmedukamel.eduai.dto.exam;

import java.time.LocalDateTime;

public record ExamResponse(
        Long id,

        String name,

        String term,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        LocalDateTime startAt,

        LocalDateTime endAt,

        Long userId,

        Integer schoolId,

        Integer semesterId,

        boolean active,

        boolean noticePublished,

        boolean resultPublished
) {
}