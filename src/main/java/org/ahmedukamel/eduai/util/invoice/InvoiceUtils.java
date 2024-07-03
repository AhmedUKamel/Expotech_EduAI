package org.ahmedukamel.eduai.util.invoice;

import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.InvoiceDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvoiceUtils {

    public static InvoiceDetail getInvoiceDetail(Invoice invoice) {
        return getInvoiceDetail(invoice, LocaleContextHolder.getLocale().getLanguage());
    }

    public static InvoiceDetail getInvoiceDetail(Invoice invoice, Language language) {
        return getInvoiceDetail(invoice, language.getCode());
    }

    public static InvoiceDetail getInvoiceDetail(Invoice invoice, String languageCode) {
        return invoice.getInvoiceDetails()
                .stream()
                .filter(invoiceDetail -> invoiceDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode.strip()))
                .findFirst()
                .orElseThrow();
    }
}
