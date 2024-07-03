package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InvoiceItemInfo(

        @NotNull
        double rate,

        @NotNull
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
