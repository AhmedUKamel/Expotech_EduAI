package org.ahmedukamel.eduai.controller.parent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.parent.AddParentRequest;
import org.ahmedukamel.eduai.service.parent.IParentService;
import org.ahmedukamel.eduai.service.parent.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping(value = "api/v1/management/parent")
public class ParentController {
    private final IParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addParent(
            @Valid @RequestBody AddParentRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/parent/new"))
                .body(parentService.addParent(request));
    }

    @DeleteMapping(value = "{parentId}")
    public ResponseEntity<?> deleteParent(
            @Min(value = 1) @PathVariable(value = "parentId") Long id) {

        return ResponseEntity.accepted().body(parentService.deleteParent(id));
    }

    @GetMapping(value = "{parentId}")
    public ResponseEntity<?> getParent(
            @Min(value = 1) @PathVariable(value = "parentId") Long id) {

        return ResponseEntity.ok().body(parentService.getParent(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllParent(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(parentService.getAllParents(pageSize, pageNumber));
    }
}