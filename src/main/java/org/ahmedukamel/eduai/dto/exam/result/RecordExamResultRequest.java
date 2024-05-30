package org.ahmedukamel.eduai.dto.exam.result;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistExam;
import org.ahmedukamel.eduai.annotation.ExistStudent;

public record RecordExamResultRequest(
        @NotNull
        @ExistStudent
        Long studentId,

        @NotNull
        @ExistExam
        Long examId,

        @NotNull
        Float score
) {
}