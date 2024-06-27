package org.ahmedukamel.eduai.dto.notification;

import java.time.LocalDateTime;

public record UserNotificationResponse(
        Long id,

        String message,

        LocalDateTime createdAt,

        boolean read
) {
}