package org.ahmedukamel.eduai.dto.api;

public record ApiResponse(
        boolean success,
        String message,
        Object data
) {
}