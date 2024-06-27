package org.ahmedukamel.eduai.controller.section;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.section.CreateSectionRequest;
import org.ahmedukamel.eduai.dto.section.UpdateSectionRequest;
import org.ahmedukamel.eduai.service.section.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/section")
public class SectionManagementController {
    private final ISectionManagementService service;

    public SectionManagementController(SectionManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createSection(@Valid @RequestBody CreateSectionRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/section"))
                .body(service.createSection(request));
    }

    @PutMapping(value = "{sectionId}")
    public ResponseEntity<?> updateSection(@Min(value = 1) @PathVariable(value = "sectionId") Integer id,
                                           @Valid @RequestBody UpdateSectionRequest request) {
        return ResponseEntity.accepted().body(service.updateSection(id, request));
    }

    @DeleteMapping(value = "{sectionId}")
    public ResponseEntity<?> deleteSection(@Min(value = 1) @PathVariable(value = "sectionId") Integer id) {
        return ResponseEntity.accepted().body(service.deleteSection(id));
    }

    @GetMapping(value = "{sectionId}")
    public ResponseEntity<?> getSection(@Min(value = 1) @PathVariable(value = "sectionId") Integer id) {
        return ResponseEntity.ok().body(service.getSection(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllSections(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllSections(pageSize, pageNumber));
    }
}