package org.ahmedukamel.eduai.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.StudyLevel;

import java.util.Set;


public record AddCourseRequest(
        @NotBlank
        String code,

        @NotNull
        StudyLevel level,

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