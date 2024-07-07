package org.ahmedukamel.eduai.dto.post;

import java.time.LocalDateTime;

public record PostCommentResponse(

        Long id,

        String creatorName,

        String creatorRole,

        String textContent,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        boolean likedByMe
) {
}
