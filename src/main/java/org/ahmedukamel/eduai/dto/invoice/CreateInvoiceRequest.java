package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateInvoiceRequest(

        @NotNull
        LocalDate dueDate,

        @NotNull
        @Min(value = 0)
        double paidAmount,

        @NotNull
        @Min(value = 0)
        double discountAmount,

        @NotNull
        @Min(value = 0)
        double taxAmount,

        String discountDescription_en,

        String taxDescription_en,

        String discountDescription_ar,

        String taxDescription_ar,

        String discountDescription_fr,

        String taxDescription_fr,

        @NotNull
        @Min(value = 1)
        Long billedToId,

        @NotNull
        List<InvoiceItemInfo> invoiceItems
) {
}
