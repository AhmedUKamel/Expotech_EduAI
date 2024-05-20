package org.ahmedukamel.eduai.dto.school;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.Language;

public record UpdateSchoolRequest(
        @NotBlank
        String name,

        @NotBlank
        String about,

        @NotNull
        Language language,

        @NotNull
        String theme
) {
}