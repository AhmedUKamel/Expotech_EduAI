package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvoiceItemResponse(

        @NotNull
        @Min(value = 0)
        Long id,

        @NotNull
        @Min(value = 0)
        double rate,

        @NotNull
        @Min(value = 1)
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
