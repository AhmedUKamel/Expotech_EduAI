package org.ahmedukamel.eduai.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.annotation.ExistUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record CreateEventRequest(
        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotNull
        @ExistUser
        Long creatorId,

        @NotNull
        LocalDateTime startDate,

        @NotNull
        LocalDateTime endDate,

        @NotBlank
        String title_en,

        @NotBlank
        String description_en,

        @NotBlank
        String title_ar,

        @NotBlank
        String description_ar,

        @NotBlank
        String title_fr,

        @NotBlank
        String description_fr,

        @NotBlank
        MultipartFile file
) {
}