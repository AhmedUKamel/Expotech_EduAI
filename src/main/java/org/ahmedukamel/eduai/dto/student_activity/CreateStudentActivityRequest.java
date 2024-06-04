package org.ahmedukamel.eduai.dto.student_activity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;

import java.time.LocalDateTime;

public record CreateStudentActivityRequest(
        @NotNull
        @ExistSchool
        Integer schoolId,

        @NotNull
        LocalDateTime dateTime,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude,

        @NotBlank
        String name_en,

        @NotBlank
        String description_en,

        @NotBlank
        String location_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String description_ar,

        @NotBlank
        String location_ar,

        @NotBlank
        String name_fr,

        @NotBlank
        String description_fr,

        @NotBlank
        String location_fr
) {
}