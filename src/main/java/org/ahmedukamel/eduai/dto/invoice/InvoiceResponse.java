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

        BilledToInfo billedToInfo,

        List<InvoiceItemResponse> invoiceItems
) {
}
