package org.ahmedukamel.eduai.dto.training_program;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;

import java.util.Date;

public record UpdateTrainingProgramRequest(
        @NotBlank
        String title_en,

        @NotBlank
        String title_ar,
        @NotBlank
        String title_fr,

        @NotBlank
        String description_en,

        @NotBlank
        String description_ar,

        @NotBlank
        String description_fr,

        @NotNull
        Date startDate,

        @NotNull
        Date endDate,

        @NotNull
        @ExistSchool
        Integer schoolId
) {
}
