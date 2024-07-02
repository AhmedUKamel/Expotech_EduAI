package org.ahmedukamel.eduai.updater.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemForUpdate;
import org.ahmedukamel.eduai.dto.invoice.UpdateInvoiceRequest;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceItem;
import org.ahmedukamel.eduai.model.InvoiceItemDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.InvoiceRepository;
import org.ahmedukamel.eduai.util.invoice.InvoiceItemUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class InvoiceUpdater implements BiFunction<Invoice, UpdateInvoiceRequest, Invoice> {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice apply(Invoice invoice, UpdateInvoiceRequest request) {

        invoice.setDueDate(request.dueDate());
        invoice.setPaidAmount(request.paidAmount());
        invoice.setDiscountAmount(request.discountAmount());

        for (InvoiceItem itemInfo :
                invoice.getInvoiceItems()) {
            List<InvoiceItemForUpdate> itemsFromRequest= request.invoiceItems();

            for (InvoiceItemForUpdate itemFromRequest:
                 itemsFromRequest) {
                if(itemFromRequest.id().equals(itemInfo.getId())){
                    InvoiceItemDetail invoiceItemDetail_en = InvoiceItemUtils.getInvoiceItemDetail(itemInfo, Language.ENGLISH);
                    invoiceItemDetail_en.setTitle(itemFromRequest.title_en());
                    invoiceItemDetail_en.setDescription(itemFromRequest.description_en());

                    InvoiceItemDetail invoiceItemDetail_ar = InvoiceItemUtils.getInvoiceItemDetail(itemInfo, Language.ARABIC);
                    invoiceItemDetail_ar.setTitle(itemFromRequest.title_ar());
                    invoiceItemDetail_ar.setDescription(itemFromRequest.description_ar());

                    InvoiceItemDetail invoiceItemDetail_fr = InvoiceItemUtils.getInvoiceItemDetail(itemInfo, Language.FRENCH);
                    invoiceItemDetail_fr.setTitle(itemFromRequest.title_fr());
                    invoiceItemDetail_fr.setDescription(itemFromRequest.description_fr());
                }
            }

        }

        return invoiceRepository.save(invoice);
    }
}
