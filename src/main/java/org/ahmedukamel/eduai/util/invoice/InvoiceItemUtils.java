package org.ahmedukamel.eduai.util.invoice;

import org.ahmedukamel.eduai.model.InvoiceItem;
import org.ahmedukamel.eduai.model.InvoiceItemDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvoiceItemUtils {

    public static InvoiceItemDetail getInvoiceItemDetail(InvoiceItem invoiceItem) {
        return getInvoiceItemDetail(invoiceItem, LocaleContextHolder.getLocale().getLanguage());
    }

    public static InvoiceItemDetail getInvoiceItemDetail(InvoiceItem invoiceItem, Language language) {
        return getInvoiceItemDetail(invoiceItem, language.getCode());
    }

    public static InvoiceItemDetail getInvoiceItemDetail(InvoiceItem invoiceItem, String languageCode) {
        return invoiceItem.getDetails()
                .stream()
                .filter(invoiceItemDetail -> invoiceItemDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode.strip()))
                .findFirst()
                .orElseThrow();
    }
}
