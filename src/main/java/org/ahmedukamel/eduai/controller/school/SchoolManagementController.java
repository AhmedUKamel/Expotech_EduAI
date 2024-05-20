package org.ahmedukamel.eduai.controller.school;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.school.CreateSchoolRequest;
import org.ahmedukamel.eduai.dto.school.UpdateSchoolRequest;
import org.ahmedukamel.eduai.service.school.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/school")
public class SchoolManagementController {
    private final ISchoolManagementService service;

    public SchoolManagementController(SchoolManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createSchool(@Valid @RequestBody CreateSchoolRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/school"))
                .body(service.createSchool(request));
    }

    @PutMapping(value = "{schoolId}")
    public ResponseEntity<?> updateSchool(@Min(value = 1) @PathVariable(value = "schoolId") Integer id,
                                          @Valid @RequestBody UpdateSchoolRequest request) {
        return ResponseEntity.accepted().body(service.updateSchool(id, request));
    }

    @DeleteMapping(value = "{schoolId}")
    public ResponseEntity<?> deleteSchool(@Min(value = 1) @PathVariable(value = "schoolId") Integer id) {
        return ResponseEntity.accepted().body(service.deleteSchool(id));
    }

    @GetMapping(value = "{schoolId}")
    public ResponseEntity<?> getSchool(@Min(value = 1) @PathVariable(value = "schoolId") Integer id) {
        return ResponseEntity.ok().body(service.getSchool(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllSchools(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                           @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllSchools(pageSize, pageNumber));
    }
}