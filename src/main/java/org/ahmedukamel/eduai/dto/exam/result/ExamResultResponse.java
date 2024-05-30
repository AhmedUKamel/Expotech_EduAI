package org.ahmedukamel.eduai.dto.exam.result;

import java.time.LocalDateTime;

public record ExamResultResponse(
        Long examResultId,

        Long studentId,

        Long examId,

        String status,

        LocalDateTime recordDate,

        Float score
) {
}