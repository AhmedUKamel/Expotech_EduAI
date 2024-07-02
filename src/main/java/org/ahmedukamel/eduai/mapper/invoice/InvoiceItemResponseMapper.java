package org.ahmedukamel.eduai.mapper.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemResponse;
import org.ahmedukamel.eduai.dto.invoice.InvoiceResponse;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceItem;
import org.ahmedukamel.eduai.model.InvoiceItemDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.invoice.InvoiceItemUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InvoiceItemResponseMapper implements Function<InvoiceItem, InvoiceItemResponse> {


    @Override
    public InvoiceItemResponse apply(InvoiceItem invoiceItem) {

        InvoiceItemDetail details_en = InvoiceItemUtils.getInvoiceItemDetail(invoiceItem, Language.ENGLISH);
        InvoiceItemDetail details_ar = InvoiceItemUtils.getInvoiceItemDetail(invoiceItem, Language.ARABIC);
        InvoiceItemDetail details_fr = InvoiceItemUtils.getInvoiceItemDetail(invoiceItem, Language.FRENCH);

        return new InvoiceItemResponse(
                invoiceItem.getRate(),
                invoiceItem.getQty(),
                details_en.getTitle(),
                details_ar.getTitle(),
                details_fr.getTitle(),
                details_en.getDescription(),
                details_ar.getDescription(),
                details_fr.getDescription()
        );
    }
}
