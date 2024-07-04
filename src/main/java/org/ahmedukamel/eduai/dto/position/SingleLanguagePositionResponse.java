package org.ahmedukamel.eduai.dto.position;

import java.time.LocalDateTime;

public record SingleLanguagePositionResponse(
        Integer id,

        String title,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Integer departmentId
) {
}