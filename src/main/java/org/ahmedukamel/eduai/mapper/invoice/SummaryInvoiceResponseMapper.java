package org.ahmedukamel.eduai.mapper.invoice;

import org.ahmedukamel.eduai.dto.invoice.SummaryInvoiceResponse;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.enumeration.PaymentStatus;
import org.ahmedukamel.eduai.util.invoice.InvoiceUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SummaryInvoiceResponseMapper implements Function<Invoice, SummaryInvoiceResponse> {

    @Override
    public SummaryInvoiceResponse apply(Invoice invoice) {
        double discount = invoice.getDiscountAmount();

        double totalFeesAmount = InvoiceUtils.getTotalItemsFeesAmount(invoice);

        totalFeesAmount += invoice.getTaxAmount();

        if(totalFeesAmount<0){
            totalFeesAmount = 0;
        }

        PaymentStatus paymentStatus = PaymentStatus.NOT_FULLY_PAID;

        if(invoice.getPaidAmount() == 0){
            paymentStatus = PaymentStatus.UNPAID;
        } else if (invoice.getPaidAmount() + discount >= totalFeesAmount) {
            paymentStatus = PaymentStatus.PAID;
        }

        return new SummaryInvoiceResponse(
                invoice.getBilledTo().getId(),
                invoice.getCreationDate(),
                invoice.getUpdateDate(),
                invoice.getDueDate(),
                invoice.getPaidAmount(),
                invoice.getBilledTo().getUsername(),
                totalFeesAmount,
                paymentStatus,
                discount
        );
    }

}
