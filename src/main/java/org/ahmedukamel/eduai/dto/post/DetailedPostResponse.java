package org.ahmedukamel.eduai.dto.post;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public record DetailedPostResponse(

        Long id,

        String creatorName,

        String creatorRole,

        String textContent,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        boolean likedByMe,

        Page<PostCommentResponse> topComments
) {
}
