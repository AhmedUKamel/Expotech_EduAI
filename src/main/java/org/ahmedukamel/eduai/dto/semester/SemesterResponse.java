package org.ahmedukamel.eduai.dto.semester;

import java.time.LocalDate;

public record SemesterResponse(
        Integer id,

        String name,

        LocalDate start,

        LocalDate end
) {
}