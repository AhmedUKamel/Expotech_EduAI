package org.ahmedukamel.eduai.dto.invoice;

import org.ahmedukamel.eduai.model.enumeration.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InvoicePublicResponse(

        Long billedToId,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        LocalDate dueDate,

        double paidAmount,

        String billedToName,

        double totalFeesAmount,

        PaymentStatus paymentStatus,

        double discountAmount
) {
}
