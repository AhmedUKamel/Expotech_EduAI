package org.ahmedukamel.eduai.dto.classes;

import java.time.LocalDateTime;

public record ClassResponse(
        Integer id,

        String name,

        String number,

        String group,

        Integer floor,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Integer schoolId
) {
}