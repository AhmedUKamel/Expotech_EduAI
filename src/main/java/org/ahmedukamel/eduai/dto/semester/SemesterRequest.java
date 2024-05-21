package org.ahmedukamel.eduai.dto.semester;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.time.LocalDate;

public record SemesterRequest(
        @NotBlank
        SemesterName name,

        @NotNull
        LocalDate start,

        @NotNull
        LocalDate end
) {
}