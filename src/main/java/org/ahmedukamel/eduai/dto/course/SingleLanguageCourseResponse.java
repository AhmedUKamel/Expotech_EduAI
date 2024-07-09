package org.ahmedukamel.eduai.dto.course;

import java.time.LocalDateTime;
import java.util.Set;


public record SingleLanguageCourseResponse(
        Long id,

        String code,

        String level,

        String name,

        String description,

        LocalDateTime creationDate,

        LocalDateTime updatedDate,

        Set<Long> prerequisiteIds
) {
}