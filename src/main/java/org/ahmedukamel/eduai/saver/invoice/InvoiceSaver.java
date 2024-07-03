package org.ahmedukamel.eduai.saver.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.invoice.CreateInvoiceRequest;
import org.ahmedukamel.eduai.dto.invoice.InvoiceItemInfo;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.InvoiceRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.ahmedukamel.eduai.util.invoice.InvoiceUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InvoiceSaver implements Function<CreateInvoiceRequest, Invoice> {
    private final InvoiceRepository invoiceRepository;
    private final StudentRepository studentRepository;

    @Override
    public Invoice apply(CreateInvoiceRequest request) {
        Student student = DatabaseService.get(studentRepository::findById, request.billedToId(), Student.class);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Invoice invoice = Invoice
                .builder()
                .billedTo(student)
                .dueDate(request.dueDate())
                .school(school)
                .discountAmount(request.discountAmount())
                .taxAmount(request.taxAmount())
                .build();


        invoice.setInvoiceDetails(new ArrayList<>());

        InvoiceDetail invoiceDetail_en = InvoiceDetail.builder()
                .invoice(invoice)
                .language(Language.ENGLISH)
                .discountDescription(request.discountDescription_en())
                .taxDescription(request.taxDescription_en())
                .build();

        InvoiceDetail invoiceDetail_ar = InvoiceDetail.builder()
                .invoice(invoice)
                .language(Language.ARABIC)
                .discountDescription(request.discountDescription_ar())
                .taxDescription(request.taxDescription_ar())
                .build();

        InvoiceDetail invoiceDetail_fr = InvoiceDetail.builder()
                .invoice(invoice)
                .language(Language.FRENCH)
                .discountDescription(request.discountDescription_fr())
                .taxDescription(request.taxDescription_fr())
                .build();

        invoice.setInvoiceDetails(Set.of(invoiceDetail_en, invoiceDetail_ar, invoiceDetail_fr));

        invoice.setInvoiceItems(new ArrayList<>());

        for (InvoiceItemInfo itemInfo :
                request.invoiceItems()) {
            InvoiceItem invoiceItem = InvoiceItem
                    .builder()
                    .qty(itemInfo.qty())
                    .rate(itemInfo.rate())
                    .invoice(invoice)
                    .build();
            InvoiceItemDetail invoiceItemDetail_en = InvoiceItemDetail
                    .builder()
                    .title(itemInfo.title_en().strip())
                    .description(itemInfo.description_en().strip())
                    .language(Language.ENGLISH)
                    .invoiceItem(invoiceItem)
                    .build(),

                    invoiceItemDetail_ar = InvoiceItemDetail
                            .builder()
                            .title(itemInfo.title_ar().strip())
                            .description(itemInfo.description_ar().strip())
                            .language(Language.ARABIC)
                            .invoiceItem(invoiceItem)
                            .build(),

                    invoiceItemDetail_fr = InvoiceItemDetail
                            .builder()
                            .title(itemInfo.title_fr().strip())
                            .description(itemInfo.description_fr().strip())
                            .language(Language.FRENCH)
                            .invoiceItem(invoiceItem)
                            .build();

            Set<InvoiceItemDetail> details = Set.of(invoiceItemDetail_en, invoiceItemDetail_ar, invoiceItemDetail_fr);
            invoiceItem.setDetails(details);
            invoice.getInvoiceItems().add(invoiceItem);
        }

        return invoiceRepository.save(invoice);
    }
}