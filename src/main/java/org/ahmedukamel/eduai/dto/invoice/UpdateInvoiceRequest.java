package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record UpdateInvoiceRequest(

        @NotBlank
        LocalDate dueDate,

        @NotBlank
        double paidAmount,

        @NotBlank
        double discountAmount,

        @NotNull
        List<InvoiceItemForUpdate> invoiceItems
) {
}
