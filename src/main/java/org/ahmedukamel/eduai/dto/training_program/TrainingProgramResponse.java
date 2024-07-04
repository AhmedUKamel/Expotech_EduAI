package org.ahmedukamel.eduai.dto.training_program;

import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.util.Date;

public record TrainingProgramResponse(
        long id,
        String title_ar,
        String title_en,
        String title_fr,
        String description_ar,
        String description_en,
        String description_fr,

        Date startDate,
        Date endDate,
        boolean deleted
) {

}
