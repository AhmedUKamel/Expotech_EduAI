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

        @NotBlank
        String discountDescription_en,

        @NotBlank
        String taxDescription_en,

        @NotBlank
        String discountDescription_ar,

        @NotBlank
        String taxDescription_ar,

        @NotBlank
        String discountDescription_fr,

        @NotBlank
        String taxDescription_fr,

        @NotNull
        @Min(value = 1)
        Long billedToId,

        @NotNull
        List<InvoiceItemInfo> invoiceItems
) {
}
