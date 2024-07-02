package org.ahmedukamel.eduai.service.invoice;

import org.ahmedukamel.eduai.dto.invoice.CreateInvoiceRequest;
import org.ahmedukamel.eduai.dto.invoice.UpdateInvoiceRequest;

public interface IInvoiceManagementService {

    Object createInvoice(Object object);

    Object updateInvoice(Long invoiceId, Object object);

    Object deleteInvoice(Long invoiceId);

    Object getInvoice(Long invoiceId);

    Object getInvoicesForSchool(int pageSize, int pageNumber);

}
