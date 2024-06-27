package org.ahmedukamel.eduai.dto.semester;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.time.LocalDate;

public record UpdateSemesterRequest(
        @NotBlank
        SemesterName name,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate
) {
}