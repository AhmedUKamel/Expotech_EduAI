package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record UpdateInvoiceRequest(

        @NotNull
        LocalDate dueDate,

        @NotNull
        @Min(value = 0)
        double paidAmount,

        @NotNull
        @Min(value = 0)
        double discountAmount,

        @NotNull
        List<InvoiceItemForUpdate> invoiceItems
) {
}
