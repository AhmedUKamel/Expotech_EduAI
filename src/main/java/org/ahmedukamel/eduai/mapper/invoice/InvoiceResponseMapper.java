package org.ahmedukamel.eduai.mapper.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemResponse;
import org.ahmedukamel.eduai.dto.invoice.InvoiceResponse;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceDetail;
import org.ahmedukamel.eduai.model.InvoiceItem;
import org.ahmedukamel.eduai.model.InvoiceItemDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.invoice.InvoiceItemUtils;
import org.ahmedukamel.eduai.util.invoice.InvoiceUtils;
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

        InvoiceDetail details_en = InvoiceUtils.getInvoiceDetail(invoice, Language.ENGLISH);
        InvoiceDetail details_ar = InvoiceUtils.getInvoiceDetail(invoice, Language.ARABIC);
        InvoiceDetail details_fr = InvoiceUtils.getInvoiceDetail(invoice, Language.FRENCH);

        return new InvoiceResponse(
                invoice.getId(),
                invoice.getCreationDate(),
                invoice.getUpdateDate(),
                invoice.getDueDate(),
                invoice.getPaidAmount(),
                invoice.getDiscountAmount(),
                invoice.getTaxAmount(),
                details_en.getDiscountDescription(),
                details_en.getTaxDescription(),
                details_ar.getDiscountDescription(),
                details_ar.getTaxDescription(),
                details_fr.getDiscountDescription(),
                details_fr.getTaxDescription(),
                billedToInfoMapper.apply(invoice.getBilledTo()),
                invoiceItemResponses
                );
    }
}
