package org.ahmedukamel.eduai.controller.classes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.classes.CreateClassRequest;
import org.ahmedukamel.eduai.dto.classes.UpdateClassRequest;
import org.ahmedukamel.eduai.service.classes.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/class")
public class ClassManagementController {
    private final IClassManagementService service;

    public ClassManagementController(ClassManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createClass(@Valid @RequestBody CreateClassRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/class"))
                .body(service.createClass(request));
    }

    @PutMapping(value = "{classId}")
    public ResponseEntity<?> updateClass(@Min(value = 1) @PathVariable(value = "classId") Integer id,
                                         @Valid @RequestBody UpdateClassRequest request) {
        return ResponseEntity.accepted().body(service.updateClass(id, request));
    }

    @DeleteMapping(value = "{classId}")
    public ResponseEntity<?> deleteClass(@Min(value = 1) @PathVariable(value = "classId") Integer id) {
        return ResponseEntity.accepted().body(service.deleteClass(id));
    }

    @GetMapping(value = "{classId}")
    public ResponseEntity<?> getClass(@Min(value = 1) @PathVariable(value = "classId") Integer id) {
        return ResponseEntity.ok().body(service.getClass(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllClasses(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                           @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllClasses(pageSize, pageNumber));
    }
}