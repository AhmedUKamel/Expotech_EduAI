package org.ahmedukamel.eduai.dto.invoice;

public record InvoiceItemResponse(

        double rate,

        int qty,

        String title_en,

        String title_ar,

        String title_fr,

        String description_en,

        String description_ar,

        String description_fr
) {
}
