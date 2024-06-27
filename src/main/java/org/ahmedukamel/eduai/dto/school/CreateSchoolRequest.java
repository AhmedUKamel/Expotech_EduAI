package org.ahmedukamel.eduai.dto.school;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.time.LocalDate;

public record CreateSchoolRequest(
        @NotBlank
        String name,

        @NotNull
        LocalDate established,

        @NotBlank
        String about,

        @NotNull
        Language language,

        @NotBlank
        String code,

        @NotBlank
        String theme
) {
}