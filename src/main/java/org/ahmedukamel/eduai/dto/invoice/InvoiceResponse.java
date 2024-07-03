package org.ahmedukamel.eduai.dto.invoice;

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

        StudentBasicInfo studentBasicInfo,

        List<InvoiceItemResponse> invoiceItems
) {
}
