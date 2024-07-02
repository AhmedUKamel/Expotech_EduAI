package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateInvoiceRequest(

        @NotBlank
        LocalDate dueDate,

        @NotBlank
        double paidAmount,

        @NotBlank
        double discountAmount,

        @NotBlank
        Long billedToId,

        @NotNull
        List<InvoiceItemInfo> invoiceItems
) {
}
