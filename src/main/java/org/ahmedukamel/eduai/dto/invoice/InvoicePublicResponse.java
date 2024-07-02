package org.ahmedukamel.eduai.dto.invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InvoicePublicResponse(

        Long id,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        LocalDate dueDate,

        double paid_amount,

        String billedToName,

        double totalFeesAmount,

        double discountAmount
) {
}
