package org.ahmedukamel.eduai.dto.semester;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SemesterRequest(
        @NotBlank
        String name,

        @NotNull
        LocalDate start,

        @NotNull
        LocalDate end
) {
}