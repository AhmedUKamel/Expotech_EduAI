package org.ahmedukamel.eduai.dto.training_program;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.model.enumeration.TargetAudience;
import org.ahmedukamel.eduai.model.enumeration.TrainingStatus;

import java.time.LocalDateTime;
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
        @NotBlank
        String objective_en,
        @NotBlank
        String objective_ar,
        @NotBlank
        String objective_fr,

        @NotNull
        Date startDate,

        @NotNull
        Date endDate,
        @NotNull
        TargetAudience targetAudience,
        @NotNull
        LocalDateTime schedule,
        @NotNull
        String duration,
        @NotNull
        String location,
        @NotNull
        TrainingStatus trainingStatus,
        @NotNull
        int Cost,
        @NotNull
        boolean deleted
) {
}
