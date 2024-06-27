package org.ahmedukamel.eduai.dto.notification;

import java.time.LocalDateTime;
import java.util.List;

public record NotificationResponse(
        Long id,

        String message_en,

        String message_ar,

        String message_fr,

        LocalDateTime createdAt,

        List<Long> usersId
) {
}