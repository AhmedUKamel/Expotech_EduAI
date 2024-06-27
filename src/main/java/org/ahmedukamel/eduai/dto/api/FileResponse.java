package org.ahmedukamel.eduai.dto.api;

import org.springframework.http.HttpHeaders;

public record FileResponse(
        byte[] date,

        HttpHeaders headers
) {
}