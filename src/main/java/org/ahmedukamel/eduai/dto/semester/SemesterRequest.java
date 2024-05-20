package org.ahmedukamel.eduai.dto.semester;

import java.time.LocalDate;

public record SemesterRequest(
        String name,

        LocalDate start,

        LocalDate end
) {
}