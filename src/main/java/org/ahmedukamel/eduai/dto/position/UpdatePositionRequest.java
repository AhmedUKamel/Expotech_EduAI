package org.ahmedukamel.eduai.dto.position;

import jakarta.validation.constraints.NotBlank;

public record UpdatePositionRequest(
        @NotBlank
        String title_en,

        @NotBlank
        String title_ar,

        @NotBlank
        String title_fr
) {
}