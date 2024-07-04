package org.ahmedukamel.eduai.service.invoice;

public interface IInvoiceManagementService {

    Object createInvoice(Object object);

    Object updateInvoice(Long invoiceId, Object object);

    Object deleteInvoice(Long invoiceId);

    Object getInvoice(Long invoiceId);

    Object getInvoicesForSchool(boolean getActive, int pageSize, int pageNumber);

}
