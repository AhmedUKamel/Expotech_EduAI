package org.ahmedukamel.eduai.dto.course;

import java.time.LocalDateTime;
import java.util.Set;


public record AllLanguagesCourseResponse(
        Long id,

        String code,

        String level,

        String name_en,

        String name_ar,

        String name_fr,

        String description_en,

        String description_ar,

        String description_fr,

        LocalDateTime creationDate,

        LocalDateTime updatedDate,

        Set<Long> prerequisiteIds
) {
}