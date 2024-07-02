package org.ahmedukamel.eduai.controller.semester;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.semester.CreateSemesterRequest;
import org.ahmedukamel.eduai.dto.semester.UpdateSemesterRequest;
import org.ahmedukamel.eduai.service.semester.ISemesterManagementService;
import org.ahmedukamel.eduai.service.semester.SemesterManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'SEMESTER_MANAGER')")
@RequestMapping(value = "api/v1/semester")
public class SemesterManagementController {
    private final ISemesterManagementService service;

    public SemesterManagementController(SemesterManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createSemester(
            @Valid @RequestBody CreateSemesterRequest request) {

        return ResponseEntity.created(URI.create("api/v1/semester"))
                .body(service.createSemester(request));
    }

    @PutMapping(value = "{semesterId}")
    public ResponseEntity<?> updateSemester(
            @Min(value = 1) @PathVariable(value = "semesterId") Integer id,
            @Valid @RequestBody UpdateSemesterRequest request) {

        return ResponseEntity.accepted().body(service.updateSemester(id, request));
    }

    @DeleteMapping(value = "{semesterId}")
    public ResponseEntity<?> deleteSemester(
            @Min(value = 1) @PathVariable(value = "semesterId") Integer id) {

        return ResponseEntity.accepted().body(service.deleteSemester(id));
    }

    @GetMapping(value = "{semesterId}")
    public ResponseEntity<?> getSemester(
            @Min(value = 1) @PathVariable(value = "semesterId") Integer id) {

        return ResponseEntity.ok().body(service.getSemester(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllSemesters(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getAllSemesters(pageSize, pageNumber));
    }
}