package org.ahmedukamel.eduai.controller.room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.room.lab.CreateLabRequest;
import org.ahmedukamel.eduai.dto.room.lab.UpdateLabRequest;
import org.ahmedukamel.eduai.service.room.lab.ILabManagementService;
import org.ahmedukamel.eduai.service.room.lab.LabManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/lab")
public class LabManagementController {
    private final ILabManagementService service;

    public LabManagementController(LabManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createLab(@Valid @RequestBody CreateLabRequest request) {
        return ResponseEntity.created(URI.create("api/v1/lab"))
                .body(service.createLab(request));
    }

    @PutMapping(value = "{labId}")
    public ResponseEntity<?> updateLab(@Min(value = 1) @PathVariable(value = "labId") Long id,
                                       @Valid @RequestBody UpdateLabRequest request) {
        return ResponseEntity.accepted().body(service.updateLab(id, request));
    }

    @DeleteMapping(value = "{labId}")
    public ResponseEntity<?> deleteLab(@Min(value = 1) @PathVariable(value = "labId") Long id) {
        return ResponseEntity.accepted().body(service.deleteLab(id));
    }

    @GetMapping(value = "{labId}")
    public ResponseEntity<?> getLab(@Min(value = 1) @PathVariable(value = "labId") Long id) {
        return ResponseEntity.ok().body(service.getLab(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllLab(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                       @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllLabs(pageSize, pageNumber));
    }
}