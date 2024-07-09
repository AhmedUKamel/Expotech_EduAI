package org.ahmedukamel.eduai.controller.parent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.exam.UpdateExamRequest;
import org.ahmedukamel.eduai.dto.parent.AddParentRequest;
import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.service.parent.IParentManagementService;
import org.ahmedukamel.eduai.service.parent.ParentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/management/parent")
public class ParentManagementController {
    private final IParentManagementService service;

    public ParentManagementController(ParentManagementService service) {
        this.service = service;
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PARENT_MANAGER')")
    @PostMapping(value = "new")
    public ResponseEntity<?> addParent(
            @Valid @RequestBody AddParentRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/parent/new"))
                .body(service.addParent(request));
    }

    @PutMapping(value = "{parentId}")
    public ResponseEntity<?> updateParent(@Valid @RequestBody UpdateParentRequest request) {
        return ResponseEntity.accepted().body(service.updateParent(request));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PARENT_MANAGER')")
    @PutMapping(value = "account-lock/{parentId}")
    public ResponseEntity<?> setParentAccountLock(
            @Min(value = 1) @PathVariable(value = "parentId") Long id,
            @RequestParam(value = "locked", defaultValue = "true") boolean accountLocked) {

        return ResponseEntity.accepted().body(service.setParentAccountLock(id, accountLocked));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PARENT_MANAGER')")
    @GetMapping(value = "{parentId}")
    public ResponseEntity<?> getParent(
            @Min(value = 1) @PathVariable(value = "parentId") Long id) {

        return ResponseEntity.ok().body(service.getParent(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PARENT_MANAGER')")
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllParents(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "archived", defaultValue = "false") boolean archived) {

        return ResponseEntity.ok().body(service.getAllParents(pageSize, pageNumber, archived));
    }
}