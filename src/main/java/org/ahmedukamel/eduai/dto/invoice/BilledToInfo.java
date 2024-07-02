package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.NotBlank;

public record BilledToInfo(

        Long id,

        String email,

        String phone
) {
}
