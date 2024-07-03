package org.ahmedukamel.eduai.dto.training_program;

import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.util.Date;

public record TrainingProgramResponse(

        long id,
        String title,
        String description,
        Date startDate,
        Date endDate,
        Integer schoolId
) {

}
