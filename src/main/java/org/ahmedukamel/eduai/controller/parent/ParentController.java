package org.ahmedukamel.eduai.controller.parent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.parent.CreateParentRequest;
import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.service.parent.IParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping(value = "api/v1/parent")
public class ParentController {
    private final IParentService parentService;

    public ParentController(IParentService parentService) {
        this.parentService = parentService;
    }
    @PostMapping
    public ResponseEntity<?> createParent(@Valid @RequestBody CreateParentRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/parent"))
                .body(parentService.createParent(request));
    }
    @PutMapping(value = "{parentId}")
    public ResponseEntity<?> updateParent(@Min(value = 1) @PathVariable(value = "examId") Long id,
                                        @Valid @RequestBody UpdateParentRequest request) {
        return ResponseEntity.accepted().body(parentService.updateParent(id, request));
    }

    @DeleteMapping(value = "{parentId}")
    public ResponseEntity<?> deleteParent(@Min(value = 1) @PathVariable(value = "parentId") Long id) {
        return ResponseEntity.accepted().body(parentService.deleteParent(id));
    }

    @GetMapping(value = "{parentId}")
    public ResponseEntity<?> getParent(@Min(value = 1) @PathVariable(value = "parentId") Long id) {
        return ResponseEntity.ok().body(parentService.getParent(id));
    }
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllParent(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                          @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(parentService.getAllParent(pageSize, pageNumber));
    }
}
