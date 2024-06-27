package org.ahmedukamel.eduai.dto.student_activity;

import java.time.LocalDateTime;

public record StudentActivityResponse(
        Long id,

        Integer schoolId,

        LocalDateTime dateTime,

        Double latitude,

        Double longitude,

        String name_en,

        String description_en,

        String location_en,

        String name_ar,

        String description_ar,

        String location_ar,

        String name_fr,

        String description_fr,

        String location_fr
) {
}