package org.ahmedukamel.eduai.controller.parent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.parent.AddParentRequest;
import org.ahmedukamel.eduai.service.parent.IParentManagementService;
import org.ahmedukamel.eduai.service.parent.ParentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PARENT_MANAGER')")
@RequestMapping(value = "api/v1/management/parent")
public class ParentManagementController {
    private final IParentManagementService service;

    public ParentManagementController(ParentManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addParent(
            @Valid @RequestBody AddParentRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/parent/new"))
                .body(service.addParent(request));
    }

    @DeleteMapping(value = "{parentId}")
    public ResponseEntity<?> deleteParent(
            @Min(value = 1) @PathVariable(value = "parentId") Long id) {

        return ResponseEntity.accepted().body(service.deleteParent(id));
    }

    @GetMapping(value = "{parentId}")
    public ResponseEntity<?> getParent(
            @Min(value = 1) @PathVariable(value = "parentId") Long id) {

        return ResponseEntity.ok().body(service.getParent(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllParents(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {

        return ResponseEntity.ok().body(service.getAllParents(pageSize, pageNumber));
    }
}