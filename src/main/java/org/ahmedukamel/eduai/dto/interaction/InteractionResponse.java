package org.ahmedukamel.eduai.dto.interaction;

import java.time.LocalDateTime;

public record InteractionResponse(
        Long id,

        String description,

        LocalDateTime creationDate,

        String type,

        Long parentId,

        Long studentId,

        Long teacherId
) {
}