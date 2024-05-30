package org.ahmedukamel.eduai.dto.notice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.annotation.ExistUser;

public record CreateNoticeRequest(
        @NotNull
        @ExistUser
        Long userId,

        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotBlank
        String title_en,

        @NotBlank
        String title_ar,

        @NotBlank
        String title_fr,

        @NotBlank
        String description_en,

        @NotBlank
        String description_ar,

        @NotBlank
        String description_fr
) {
}