package org.ahmedukamel.eduai.mapper.invoice;

import org.ahmedukamel.eduai.dto.invoice.InvoicePublicResponse;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InvoicePublicResponseMapper implements Function<Invoice, InvoicePublicResponse> {
    @Override
    public InvoicePublicResponse apply(Invoice invoice) {
        double totalFeesAmount = 0;
        double discount = invoice.getDiscountAmount();

        for (InvoiceItem invoiceItem :
                invoice.getInvoiceItems()) {
            totalFeesAmount += invoiceItem.getRate() * invoiceItem.getQty();
        }

        totalFeesAmount -= discount;
        totalFeesAmount += invoice.getTaxAmount();

        if(totalFeesAmount<0){
            totalFeesAmount = 0;
        }

        return new InvoicePublicResponse(
                invoice.getBilledTo().getId(),
                invoice.getCreationDate(),
                invoice.getUpdateDate(),
                invoice.getDueDate(),
                invoice.getPaidAmount(),
                invoice.getBilledTo().getUsername(),
                totalFeesAmount,
                discount
        );
    }
}
