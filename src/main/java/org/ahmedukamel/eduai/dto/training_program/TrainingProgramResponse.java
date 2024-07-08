package org.ahmedukamel.eduai.dto.training_program;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.SemesterName;
import org.ahmedukamel.eduai.model.enumeration.TargetAudience;
import org.ahmedukamel.eduai.model.enumeration.TrainingStatus;

import java.time.LocalDateTime;
import java.util.Date;

public record TrainingProgramResponse(
        long id,
        String title_ar,
        String title_en,
        String title_fr,
        String description_ar,
        String description_en,
        String description_fr,
        String objective_en,
        String objective_ar,
        String objective_fr,
        Date startDate,
        Date endDate,
        TargetAudience targetAudience,
        String schedule,
        String duration,
        String location,
        TrainingStatus trainingStatus,
        int Cost,
        boolean deleted
) {

}
