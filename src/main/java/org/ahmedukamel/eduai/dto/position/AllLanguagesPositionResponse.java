package org.ahmedukamel.eduai.dto.position;

import java.time.LocalDateTime;

public record AllLanguagesPositionResponse(
        Integer id,

        String title_en,

        String title_ar,

        String title_fr,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Integer departmentId
) {
}