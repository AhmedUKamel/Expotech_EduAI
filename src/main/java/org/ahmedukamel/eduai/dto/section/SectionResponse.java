package org.ahmedukamel.eduai.dto.section;

import java.time.LocalDateTime;
import java.util.Collection;

public record SectionResponse(
        Integer id,

        String name,

        String number,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Long classroomId,

        Collection<Long> studentsId
) {
}