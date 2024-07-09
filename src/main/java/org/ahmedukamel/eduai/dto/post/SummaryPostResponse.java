package org.ahmedukamel.eduai.dto.post;

import java.time.LocalDateTime;
import java.util.List;

public record SummaryPostResponse(

        Long id,

        String creatorName,

        String creatorRole,

        String textContent,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        boolean viewedByMe
) {
}
