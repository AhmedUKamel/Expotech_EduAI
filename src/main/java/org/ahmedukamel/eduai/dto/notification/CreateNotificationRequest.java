package org.ahmedukamel.eduai.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateNotificationRequest(
        @NotBlank
        String message_en,

        @NotBlank
        String message_ar,

        @NotBlank
        String message_fr,

        @NotNull
        @NotEmpty
        Set<Long> userIds
) {
}