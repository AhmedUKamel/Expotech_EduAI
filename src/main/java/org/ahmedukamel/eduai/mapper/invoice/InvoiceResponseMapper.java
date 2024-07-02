package org.ahmedukamel.eduai.mapper.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemResponse;
import org.ahmedukamel.eduai.dto.invoice.InvoiceResponse;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InvoiceResponseMapper implements Function<Invoice, InvoiceResponse> {

    private final BilledToInfoMapper billedToInfoMapper;
    private final InvoiceItemResponseMapper invoiceItemResponseMapper;

    @Override
    public InvoiceResponse apply(Invoice invoice) {
        List<InvoiceItemResponse> invoiceItemResponses = new ArrayList<>();

        for (InvoiceItem invoiceItem:
                invoice.getInvoiceItems()) {
            invoiceItemResponses.add(invoiceItemResponseMapper.apply(invoiceItem));
        }

        return new InvoiceResponse(
                invoice.getId(),
                invoice.getCreationDate(),
                invoice.getUpdateDate(),
                invoice.getDueDate(),
                invoice.getPaidAmount(),
                billedToInfoMapper.apply(invoice.getBilledTo()),
                invoiceItemResponses
                );
    }
}
