package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.NotBlank;

public record InvoiceItemForUpdate(

        @NotBlank
        Long id,

        @NotBlank
        double rate,

        @NotBlank
        int qty,

        @NotBlank
        String title_en,

        @NotBlank
        String title_ar,

        @NotBlank
        String title_fr,

        @NotBlank
        String description_en,

        @NotBlank
        String description_ar,

        @NotBlank
        String description_fr
) {
}
