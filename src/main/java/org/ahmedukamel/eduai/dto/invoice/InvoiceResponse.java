package org.ahmedukamel.eduai.dto.invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceResponse(

        Long id,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        LocalDate dueDate,

        double paid_amount,

        double discountAmount,

        double taxAmount,

        String discountDescription_en,

        String taxDescription_en,

        String discountDescription_ar,

        String taxDescription_ar,

        String discountDescription_fr,

        String taxDescription_fr,

        BilledToInfo billedToInfo,

        List<InvoiceItemResponse> invoiceItems
) {
}
