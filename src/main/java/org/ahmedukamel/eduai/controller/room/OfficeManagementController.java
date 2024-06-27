package org.ahmedukamel.eduai.controller.room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.room.office.CreateOfficeRequest;
import org.ahmedukamel.eduai.dto.room.office.UpdateOfficeRequest;
import org.ahmedukamel.eduai.service.room.office.IOfficeManagementService;
import org.ahmedukamel.eduai.service.room.office.OfficeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/office")
public class OfficeManagementController {
    private final IOfficeManagementService service;

    public OfficeManagementController(OfficeManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createOffice(@Valid @RequestBody CreateOfficeRequest request) {
        return ResponseEntity.created(URI.create("api/v1/office"))
                .body(service.createOffice(request));
    }

    @PutMapping(value = "{officeId}")
    public ResponseEntity<?> updateOffice(@Min(value = 1) @PathVariable(value = "officeId") Long id,
                                       @Valid @RequestBody UpdateOfficeRequest request) {
        return ResponseEntity.accepted().body(service.updateOffice(id, request));
    }

    @DeleteMapping(value = "{officeId}")
    public ResponseEntity<?> deleteOffice(@Min(value = 1) @PathVariable(value = "officeId") Long id) {
        return ResponseEntity.accepted().body(service.deleteOffice(id));
    }

    @GetMapping(value = "{officeId}")
    public ResponseEntity<?> getOffice(@Min(value = 1) @PathVariable(value = "officeId") Long id) {
        return ResponseEntity.ok().body(service.getOffice(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllOffice(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                       @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllOffices(pageSize, pageNumber));
    }
}