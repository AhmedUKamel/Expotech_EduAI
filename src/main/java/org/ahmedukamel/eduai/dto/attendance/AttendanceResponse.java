package org.ahmedukamel.eduai.dto.attendance;

import java.time.LocalDate;

public record AttendanceResponse(
        Long id,

        Long studentId,

        Integer sectionId,

        String status,

        LocalDate date
) {
}