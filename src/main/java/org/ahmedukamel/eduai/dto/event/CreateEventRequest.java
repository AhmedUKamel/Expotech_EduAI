package org.ahmedukamel.eduai.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.annotation.ExistUser;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventRequest(
        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotNull
        @ExistUser
        Long creatorId,

        @NotNull
        LocalDateTime startTime,

        @NotNull
        LocalDateTime endTime,

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

        List<Long> attendeesId
) {
}