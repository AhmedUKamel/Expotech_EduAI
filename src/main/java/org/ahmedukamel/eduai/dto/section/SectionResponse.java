package org.ahmedukamel.eduai.dto.section;

import java.time.LocalDateTime;

public record SectionResponse(
        Integer id,

        String name,

        String number,

        String roomNumber,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Integer classId,

        Long userId
) {
}