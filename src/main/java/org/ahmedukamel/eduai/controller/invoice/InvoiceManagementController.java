package org.ahmedukamel.eduai.controller.invoice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.invoice.CreateInvoiceRequest;
import org.ahmedukamel.eduai.dto.invoice.UpdateInvoiceRequest;
import org.ahmedukamel.eduai.service.invoice.IInvoiceManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN' ,'INVOICE_MANAGER')")
@RequestMapping(value = "api/v1/invoice")
public class InvoiceManagementController {
    
    private final IInvoiceManagementService service;


    public InvoiceManagementController(IInvoiceManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@Valid @RequestBody CreateInvoiceRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/invoice"))
                .body(service.createInvoice(request));
    }

    @PutMapping(value = "{invoiceId}")
    public ResponseEntity<?> updateInvoice(@Min(value = 1) @PathVariable(value = "invoiceId") Long id,
                                        @Valid @RequestBody UpdateInvoiceRequest request) {
        return ResponseEntity.accepted().body(service.updateInvoice(id, request));
    }

    @DeleteMapping(value = "{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@Min(value = 1) @PathVariable(value = "invoiceId") Long id) {
        return ResponseEntity.accepted().body(service.deleteInvoice(id));
    }

    @GetMapping(value = "{invoiceId}")
    public ResponseEntity<?> getInvoice(@Min(value = 1) @PathVariable(value = "invoiceId") Long id) {
        return ResponseEntity.ok().body(service.getInvoice(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllInvoices(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                            @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getInvoicesForSchool(getActive, pageSize, pageNumber));
    }
}
