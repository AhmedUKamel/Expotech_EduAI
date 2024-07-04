package org.ahmedukamel.eduai.dto.invoice;

import org.ahmedukamel.eduai.model.enumeration.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceResponse(

        Long id,

        LocalDateTime creationDate,

        LocalDateTime updateDate,

        LocalDate dueDate,

        double totalAmount,

        double paidAmount,

        double discountAmount,

        double taxAmount,

        double dueAmount,

        String discountDescription_en,

        String taxDescription_en,

        String discountDescription_ar,

        String taxDescription_ar,

        String discountDescription_fr,

        String taxDescription_fr,

        StudentBasicInfo studentBasicInfo,

        PaymentStatus paymentStatus,

        List<InvoiceItemResponse> invoiceItems
) {
}
