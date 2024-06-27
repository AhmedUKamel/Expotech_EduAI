package org.ahmedukamel.eduai.dto.semester;

import org.ahmedukamel.eduai.model.enumeration.SemesterName;

import java.time.LocalDate;

public record SemesterResponse(
        Integer id,

        SemesterName name,

        LocalDate startDate,

        LocalDate endDate
) {
}