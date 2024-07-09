package org.ahmedukamel.eduai.service.invoice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.invoice.CreateInvoiceRequest;
import org.ahmedukamel.eduai.dto.invoice.SummaryInvoiceResponse;
import org.ahmedukamel.eduai.dto.invoice.InvoiceResponse;
import org.ahmedukamel.eduai.dto.invoice.UpdateInvoiceRequest;
import org.ahmedukamel.eduai.mapper.invoice.SummaryInvoiceResponseMapper;
import org.ahmedukamel.eduai.mapper.invoice.InvoiceResponseMapper;
import org.ahmedukamel.eduai.model.Invoice;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.InvoiceRepository;
import org.ahmedukamel.eduai.saver.invoice.InvoiceSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.invoice.InvoiceUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceManagementService implements IInvoiceManagementService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceSaver invoiceSaver;
    private final InvoiceUpdater invoiceUpdater;
    private final InvoiceResponseMapper invoiceResponseMapper;
    private final SummaryInvoiceResponseMapper summaryInvoiceResponseMapper;


    @Override
    public Object createInvoice(Object object){
        CreateInvoiceRequest request = (CreateInvoiceRequest) object;

        Invoice savedInvoice = invoiceSaver.apply(request);

        InvoiceResponse response = invoiceResponseMapper.apply(savedInvoice);
        String message = "Invoice created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateInvoice(Long invoiceId, Object object){
        Invoice invoice = DatabaseService.get(invoiceRepository::findById, invoiceId, Invoice.class);
        UpdateInvoiceRequest request = (UpdateInvoiceRequest) object;

        Invoice savedInvoice = invoiceUpdater.apply(invoice, request);

        InvoiceResponse response = invoiceResponseMapper.apply(savedInvoice);
        String message = "Invoice Updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteInvoice(Long invoiceId) {
        Invoice invoice = DatabaseService.get(invoiceRepository::findById, invoiceId, Invoice.class);

        invoice.setDeleted(true);

        invoiceRepository.save(invoice);

        String message = "Invoice deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getInvoice(Long invoiceId) {
        Invoice invoice = DatabaseService.get(invoiceRepository::findById, invoiceId, Invoice.class);

        InvoiceResponse response = invoiceResponseMapper.apply(invoice);

        String message = "Invoice retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getInvoicesForSchool(boolean getActive, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();
        Page<Invoice> invoices = invoiceRepository
                .findAllBySchoolIdAndDeletedOrderByUpdateDate(school.getId(), !getActive, pageable);

        Page<SummaryInvoiceResponse> response = invoices
                .map(summaryInvoiceResponseMapper);

        String status = getActive? "Active":"Deleted";
        String message = status + " Invoices retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

}
