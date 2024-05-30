package org.ahmedukamel.eduai.dto.interaction;

public record CreateInteractionRequest(
        Long userId,

        String description
) {
}