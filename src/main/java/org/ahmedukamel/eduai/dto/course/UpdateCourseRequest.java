package org.ahmedukamel.eduai.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


public record UpdateCourseRequest(
        @NotBlank
        String name_en,

        @NotBlank
        String name_ar,

        @NotBlank
        String name_fr,

        @NotBlank
        String description_en,

        @NotBlank
        String description_ar,

        @NotBlank
        String description_fr,

        @NotNull
        Set<Long> prerequisiteIds
) {
}