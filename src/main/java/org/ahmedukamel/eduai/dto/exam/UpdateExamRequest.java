package org.ahmedukamel.eduai.dto.exam;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.annotation.ExistSemester;
import org.ahmedukamel.eduai.annotation.ExistUser;

import java.time.LocalDateTime;

public record UpdateExamRequest(
        @NotBlank
        String name,

        @NotBlank
        String term,

        @NotNull
        LocalDateTime startAt,

        @NotNull
        LocalDateTime endAt,

        @NotNull
        @ExistUser
        Long userId,

        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotNull
        @ExistSemester
        Integer semesterId,

        @NotNull
        Boolean active,

        @NotNull
        Boolean noticePublished,

        @NotNull
        Boolean resultPublished
) {
}