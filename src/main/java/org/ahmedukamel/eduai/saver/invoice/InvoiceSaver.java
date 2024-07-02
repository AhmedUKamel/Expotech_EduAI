package org.ahmedukamel.eduai.saver.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.CreateInvoiceRequest;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemInfo;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.InvoiceRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InvoiceSaver implements Function<CreateInvoiceRequest, Invoice> {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    @Override
    public Invoice apply(CreateInvoiceRequest request) {
        User user = DatabaseService.get(userRepository::findById, request.billedToId(), User.class);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Invoice invoice = Invoice
                .builder()
                .billedTo(user)
                .school(school)
                .discountAmount(request.discountAmount())
                .build();

        for (InvoiceItemInfo itemInfo :
                request.invoiceItems()) {
            InvoiceItem invoiceItem = InvoiceItem
                    .builder()
                    .qty(itemInfo.qty())
                    .rate(itemInfo.rate())
                    .invoice(invoice)
                    .build();
            InvoiceItemDetail invoiceDetail_en = InvoiceItemDetail
                    .builder()
                    .title(itemInfo.title_en().strip())
                    .description(itemInfo.description_en().strip())
                    .language(Language.ENGLISH)
                    .invoiceItem(invoiceItem)
                    .build(),

                    invoiceDetail_ar = InvoiceItemDetail
                            .builder()
                            .title(itemInfo.title_ar().strip())
                            .description(itemInfo.description_ar().strip())
                            .language(Language.ARABIC)
                            .invoiceItem(invoiceItem)
                            .build(),

                    invoiceDetail_fr = InvoiceItemDetail
                            .builder()
                            .title(itemInfo.title_fr().strip())
                            .description(itemInfo.description_fr().strip())
                            .language(Language.FRENCH)
                            .invoiceItem(invoiceItem)
                            .build();

            Set<InvoiceItemDetail> details = Set.of(invoiceDetail_en, invoiceDetail_ar, invoiceDetail_fr);
            invoiceItem.setDetails(details);
            invoice.getInvoiceItems().add(invoiceItem);
        }

        return invoiceRepository.save(invoice);
    }
}